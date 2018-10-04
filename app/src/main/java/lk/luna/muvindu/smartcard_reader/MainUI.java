package lk.luna.muvindu.smartcard_reader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import be.appfoundry.nfclibrary.activities.NfcActivity;
import lk.luna.muvindu.smartcard_reader.Models.Route;
import lk.luna.muvindu.smartcard_reader.ServiceClasses.ApiService;
import lk.luna.muvindu.smartcard_reader.ServiceClasses.JourneyService;

import static lk.luna.muvindu.smartcard_reader.SelectionMenu.Halts;
import static lk.luna.muvindu.smartcard_reader.SelectionMenu.journeyService;
import static lk.luna.muvindu.smartcard_reader.SelectionMenu.selectedRoute;

public class MainUI extends NfcActivity {
    public static boolean journeyStatus=false;
    private ApiService apiService=new ApiService();
    private TextView next,previous,current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        next= (TextView) findViewById(R.id.nextStop);
        current=(TextView) findViewById(R.id.currentStop);
        previous=(TextView) findViewById(R.id.previousStop);


        next.setText(Halts.get(journeyService.getRoute().getHaltList().get(2)));
        current.setText(Halts.get(journeyService.getRoute().getHaltList().get(1)));



    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        for (String message : getNfcMessages()){
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }
    }

    private void getCardDetails(String cardNumber){

    }


}
