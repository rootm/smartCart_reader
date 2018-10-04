package lk.luna.muvindu.smartcard_reader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import lk.luna.muvindu.smartcard_reader.Models.Bus;
import lk.luna.muvindu.smartcard_reader.Models.Halt;
import lk.luna.muvindu.smartcard_reader.Models.Route;
import lk.luna.muvindu.smartcard_reader.ServiceClasses.ApiService;
import lk.luna.muvindu.smartcard_reader.ServiceClasses.JourneyService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SelectionMenu extends AppCompatActivity {
    public static JourneyService journeyService=new JourneyService();
    public static Bus selectedBus;
    public static Route selectedRoute;
    public static HashMap<Integer,String> Halts=new HashMap<Integer, String>();
    private Spinner routeSelect;
    private Spinner busSelect;
    private ApiService apiService=new ApiService();
    private ArrayList<Route> routeList=new ArrayList<>();
    private ArrayList<Bus> busList=new ArrayList<>();
    public static String DEFAULT_API_ROOT="https://ticketing-api.herokuapp.com/";
    private LinearLayout layout;
    private ArrayList<String> routeNames=new ArrayList<>();
    private ArrayList<String> busNames=new ArrayList<>();
    private Button save ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_menu);


        busSelect = (Spinner) findViewById(R.id.busSpinner);
        routeSelect = (Spinner) findViewById(R.id.routeSpinner);
        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        layout.bringToFront();
        save =(Button) findViewById(R.id.saveSettings);
        save =(Button) findViewById(R.id.saveSettings);


        getRouteList();



        routeSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1, int arg2, long arg3) {
                int index = routeSelect.getSelectedItemPosition();
                Toast.makeText(getApplicationContext(),"adapter set for route list",Toast.LENGTH_SHORT).show();

                busSelect.setEnabled(false);
                journeyService.setRoute(getRoute(routeNames.get(index)));
               // selectedRoute=getRoute(routeNames.get(index));
                getBusList(String.valueOf(getRouteID(routeNames.get(index))));
//
            }


            public void onNothingSelected(AdapterView<?> arg0) {}
        });



        busSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1, int arg2, long arg3) {
                int index = routeSelect.getSelectedItemPosition();

                selectedBus=getBus(busNames.get(index));

                Toast.makeText(getApplicationContext(),"adapter set for bus list",Toast.LENGTH_SHORT).show();
            }


            public void onNothingSelected(AdapterView<?> arg0) {}
        });



    }

    private void getRouteList(){

        //routeList.clear();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DEFAULT_API_ROOT+"routes/")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Request", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                routeList.clear();
                String jsonData = response.body().string();
                JSONArray dataArray=new JSONArray();
                try {

                    JSONObject obj = new JSONObject(jsonData);

                    dataArray = obj.getJSONArray("message");


                    for(int i=0;i< dataArray.length();i++){
                        Route route=new Route();
                        route.setRouteId(dataArray.getJSONObject(i).getInt("routeId"));
                        route.setRouteName(dataArray.getJSONObject(i).getString("routeName"));
                        route.setCost(dataArray.getJSONObject(i).getDouble("priceKm"));
                        routeList.add(route);
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setRouteSpinnerAdapter();
                        }
                    });


                }catch (Exception ex){
                    Log.e("sss",ex.getMessage());
                }
            }




        });
    }



    private void getBusList(String id){

        busList.clear();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DEFAULT_API_ROOT+"routes/bus/"+id)
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

                    JSONObject obj = new JSONObject(jsonData);

                    dataArray = obj.getJSONArray("data");


                    for(int i=0;i< dataArray.length();i++){
                        Bus bus=new Bus();
                        bus.setBusId(dataArray.getJSONObject(i).getInt("busId"));
                        bus.setBusNo(dataArray.getJSONObject(i).getString("busNo"));
                        busList.add(bus);
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setBusSpinnerAdapter();
                        }
                    });


                }catch (Exception ex){
                    Log.e("sss",ex.getMessage());
                }
            }




        });
    }

    private void setRouteSpinnerAdapter(){




        busNames.clear();
        for (Route route:routeList){
            routeNames.add(route.getRouteName());
        }


        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,routeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSelect.setAdapter(adapter);

    }


    private void setBusSpinnerAdapter(){




        for (Bus bus:busList){
            busNames.add(bus.getBusNo());
        }


        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,busNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busSelect.setAdapter(adapter);
        save.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
    }

    private int getRouteID(String name){
        for (Route route:routeList){
            if (route.getRouteName().equals(name)){
                busSelect.setEnabled(true);
                return route.getRouteId();
            }
        }
        return -1;
    }


    private Route getRoute(String name){
        for (Route route:routeList){
            if (route.getRouteName().equals(name)){

                return route;
            }
        }
        return null;
    }

    private Bus getBus(String name){
        for (Bus bus:busList){
            if (bus.getBusNo().equals(name)){

                return bus;
            }
        }
        return null;
    }


    private void getHalts(){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DEFAULT_API_ROOT+"stations/")
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

                    JSONObject obj = new JSONObject(jsonData);

                    dataArray = obj.getJSONArray("message");


                    for(int i=0;i< dataArray.length();i++){
                        int id;
                        String name;
                        id=(dataArray.getJSONObject(i).getInt("stationId"));
                        name=(dataArray.getJSONObject(i).getString("stationName"));

                        Halts.put(id,name);
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(getBaseContext(), MainUI.class);

                            startActivity(i);

                        }
                    });


                }catch (Exception ex){
                    Log.e("sss",ex.getMessage());
                }
            }




        });
    }

    public void saveSettings(View view) {

        save.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
        HashMap<Integer,Integer> haltList=new HashMap<>();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(DEFAULT_API_ROOT+"routes/stations/"+journeyService.getRoute().getRouteId())
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

                    JSONObject obj = new JSONObject(jsonData);

                    dataArray = obj.getJSONArray("data");


                    for(int i=0;i< dataArray.length();i++){
                         Halt halt=new Halt();
//                        halt.setStationId(dataArray.getJSONObject(i).getString("stationId"));
//                        halt.setIndex(dataArray.getJSONObject(i).getInt("routeOrder"));

                        haltList.put(dataArray.getJSONObject(i).getInt("routeOrder"),dataArray.getJSONObject(i).getInt("stationId"));
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            journeyService.getRoute().setHaltList(haltList);
                            getHalts();
                        }
                    });


                }catch (Exception ex){
                    Log.e("sss",ex.getMessage());
                }
            }




        });

    }
}
