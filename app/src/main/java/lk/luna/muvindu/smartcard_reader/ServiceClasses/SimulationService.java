package lk.luna.muvindu.smartcard_reader.ServiceClasses;

import android.os.Handler;
import android.widget.TextView;

import java.util.HashMap;

import lk.luna.muvindu.smartcard_reader.Models.Journey;

import static lk.luna.muvindu.smartcard_reader.SelectionMenu.Halts;
import static lk.luna.muvindu.smartcard_reader.SelectionMenu.journeyService;
import static lk.luna.muvindu.smartcard_reader.ServiceClasses.JourneyService.getJourneyList;
import static lk.luna.muvindu.smartcard_reader.ServiceClasses.RealTimeDB_Service.CURRENT_STOP;
import static lk.luna.muvindu.smartcard_reader.ServiceClasses.RealTimeDB_Service.NEXT_STOP;
import static lk.luna.muvindu.smartcard_reader.ServiceClasses.RealTimeDB_Service.PREVIOUS_STOP;

public class SimulationService {
    private HashMap<Integer,String> stations=new HashMap<>();
    private static int currentId=1;
    public static boolean stoped=true;
    public static String Current_station;
    public static String next_station;
    public static String previous_station;;
    private RealTimeDB_Service realTimeDB_service=new RealTimeDB_Service();


    public void change(TextView next,TextView previous,TextView current){
        currentId=1;
        stoped=true;
        next.setText("None");
        previous.setText("None");
        current.setText("None");
        setStations();
        final Handler handler = new Handler();


        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(stoped){
                    Current_station=stations.get(currentId);
                    setStationsDB(CURRENT_STOP,Current_station);
                    current.setText(Current_station);
                    if(currentId==stations.size()){
                        next.setText("None");
                        setStationsDB(NEXT_STOP,"None");
                    }else{
                        next.setText(stations.get(currentId+1));
                        setStationsDB(NEXT_STOP,next.getText().toString());
                    }

                    if(currentId!=1){
                        previous.setText(stations.get(currentId-1));
                        setStationsDB(PREVIOUS_STOP,previous.getText().toString());
                    }else{
                        previous.setText("None");
                        setStationsDB(PREVIOUS_STOP,previous.getText().toString());
                    }
                    ++currentId;
                    if(!(currentId>stations.size())) {
                        stoped=false;
                        handler.postDelayed(this, 10000L);  // 1 second delay
                        }
                    }else{

                    current.setText("None");
                    setStationsDB(CURRENT_STOP,"None");
                    if(!(currentId>stations.size())) {
                        stoped=true;
                        handler.postDelayed(this, 7000L);  // 1 second delay
                    }
                }
            }
        };
        runnable.run();





    }

    private HashMap<Integer,String> setStations(){
        //Halts.get(journeyService.getRoute().getHaltList().get(2));

        for(int i=1;i<=journeyService.getRoute().getHaltList().size();i++){
            stations.put(i, Halts.get(journeyService.getRoute().getHaltList().get(i)));
        }

        return stations;
    }

    private void setStationsDB(String type,String station){
        for(String card: getJourneyList().keySet() ){
            realTimeDB_service.changeStation(card,type,station);

        }
    }

}
