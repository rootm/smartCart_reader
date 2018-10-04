package lk.luna.muvindu.smartcard_reader;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.SoundEffectConstants;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import be.appfoundry.nfclibrary.activities.NfcActivity;
import lk.luna.muvindu.smartcard_reader.Models.Bus;
import lk.luna.muvindu.smartcard_reader.Models.Card;
import lk.luna.muvindu.smartcard_reader.Models.Route;
import lk.luna.muvindu.smartcard_reader.ServiceClasses.ApiService;
import lk.luna.muvindu.smartcard_reader.ServiceClasses.JourneyService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static lk.luna.muvindu.smartcard_reader.SelectionMenu.DEFAULT_API_ROOT;
import static lk.luna.muvindu.smartcard_reader.SelectionMenu.Halts;
import static lk.luna.muvindu.smartcard_reader.SelectionMenu.journeyService;
import static lk.luna.muvindu.smartcard_reader.SelectionMenu.selectedRoute;

public class MainUI extends NfcActivity {
    public static boolean journeyStatus=false;
    private ApiService apiService=new ApiService();
    private AudioManager audioManager;
    private TextView next,previous,current,pass_Status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        next= (TextView) findViewById(R.id.nextStop);
        current=(TextView) findViewById(R.id.currentStop);
        previous=(TextView) findViewById(R.id.previousStop);
        pass_Status =(TextView) findViewById(R.id.passStatus);

        next.setText(Halts.get(journeyService.getRoute().getHaltList().get(2)));
        current.setText(Halts.get(journeyService.getRoute().getHaltList().get(1)));



    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (String message : getNfcMessages()){

            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audioManager.playSoundEffect(SoundEffectConstants.CLICK,1F);
            getCardDetails(message.replace("en",""));

        }
    }

    private void getCardDetails(String cardNumber){

        Card card=new Card();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DEFAULT_API_ROOT+"card/"+cardNumber)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Request", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                JSONArray dataArray=new JSONArray();
                try {

                    if(!jsonData.equals("{\"data\":null}")){
                        JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("data");

                        //dataArray = obj.getJSONArray("data");


                       // for(int i=0;i< dataArray.length();i++){
                          //  {"data":{"cardId":6,"accountId":2,"pin":456,"dateIssued":"2018-10-03","balance":1205,"onLoan":0,"expireDate":"2018-10-10","tempory":0,"createdAt":null,"updatedAt":null}}

                            card.setAccountId(jsonObject.getString("accountId"));
                            card.setCardNumber(jsonObject.getString("cardId"));
                            card.setDateissued(jsonObject.getString("dateIssued"));
                            card.setExpire(jsonObject.getString("expireDate"));
                            card.setBalance(jsonObject.getInt("balance"));
                       // }


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!(card.getBalance() < journeyService.getRoute().getCost())){
                                    pass_Status.setText("Valid Pass");
                                }
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //if(!(card.getBalance() < journeyService.getRoute().getCost())){
                                    pass_Status.setText("Invalid Pass");
                                //}
                            }
                        });
                    }



                }catch (Exception ex){
                    Log.e("sss",ex.getMessage());
                }
            }




        });

    }


}
