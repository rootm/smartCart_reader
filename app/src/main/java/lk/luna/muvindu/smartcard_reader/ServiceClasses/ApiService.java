package lk.luna.muvindu.smartcard_reader.ServiceClasses;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import lk.luna.muvindu.smartcard_reader.Models.Bus;
import lk.luna.muvindu.smartcard_reader.Models.Route;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiService {

    private static String DEFAULT_API_ROOT="https://ticketing-api.herokuapp.com/";


    public void getRouteList(){


        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(DEFAULT_API_ROOT+"routes/")
//                .build();
//
//
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("Request", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                list.clear();
//                String jsonData = response.body().string();
//                JSONArray dataArray=new JSONArray();
//                try {
//
//                    JSONObject obj = new JSONObject(jsonData);
//
//                    dataArray = obj.getJSONArray("message");
//
//
//                    for(int i=0;i< dataArray.length();i++){
//                        Route route=new Route();
//                        route.setRouteId(dataArray.getJSONObject(i).getInt("routeId"));
//                        route.setRouteName(dataArray.getJSONObject(i).getString("routeName"));
//                        routeList.add(route);
//                    }
//
//
//                }catch (Exception ex){
//                    Log.e("sss",ex.getMessage());
//                }
//            }
//
//
//
//
//        });
//       // return null;
    }

    public ArrayList<Bus> getBusList(String routeId){

        return null;

    }



}
