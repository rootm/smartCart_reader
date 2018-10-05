package lk.luna.muvindu.smartcard_reader.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import lk.luna.muvindu.smartcard_reader.ServiceClasses.Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static lk.luna.muvindu.smartcard_reader.SelectionMenu.DEFAULT_API_ROOT;

public class Journey {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private Utils utils=new Utils();
    private String onBoard;
    private String exit;
    private String route;
    private String routeNo;
    private String onBoardTime;
    private String endTime;
    private int cost;

public Journey(){
   this.onBoard="";
    this.exit="";
    this.route="";
    this.routeNo="";
    this. onBoardTime="";
    this.endTime="";

}

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }



    public void save(){

        JSONObject journey = new JSONObject();
        try {

            journey.put("accountId", 3);
            journey.put("routeId", Integer.valueOf(this.routeNo));
            journey.put("startStation", this. onBoard);
            journey.put("endStation", this.exit);
            journey.put("date", utils.currentDate());
            journey.put("startTime", this. onBoardTime);
            journey.put("endTime", this.endTime);
            journey.put("cost", this.cost);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, journey.toString());

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DEFAULT_API_ROOT+"journey/")
                .post(body)
                .build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Request", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonData = response.body().string();
                Log.e("sss",jsonData);
                JSONArray dataArray=new JSONArray();
                try {

                    JSONObject obj = new JSONObject(jsonData);


                }catch (Exception ex){
                    Log.e("sss",ex.getMessage());
                }
            }




        });
    }


    /**
     * Getters and setters for private Attributes
     * @return
     */

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }



    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOnBoard() {
        return onBoard;
    }

    public void setOnBoard(String onBoard) {
        this.onBoard = onBoard;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }

    public String getOnBoardTime() {
        return onBoardTime;
    }

    public void setOnBoardTime(String onBoardTime) {
        this.onBoardTime = onBoardTime;
    }
}
