package lk.luna.muvindu.smartcard_reader.ServiceClasses;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import lk.luna.muvindu.smartcard_reader.Models.Journey;
import lk.luna.muvindu.smartcard_reader.Models.Route;

import static lk.luna.muvindu.smartcard_reader.ServiceClasses.SimulationService.Current_station;

public class JourneyService {

  private static HashMap<String, Journey> journeyList=new HashMap<>();
  private Route route;
  private RealTimeDB_Service realTimeDB_service=new RealTimeDB_Service();
  private Utils utils=new Utils();

    /**
     * Call when a commuter getOn the bus
     * @param cardNumber read from the cardReader
     */
  public void newJourney(String cardNumber,Journey journey){
      //Journey journey  =new Journey();
      if(!journeyList.containsKey(cardNumber)){
        journeyList.put(cardNumber,journey);
      }
      realTimeDB_service.addJourney(cardNumber,journey);


  }



    /**
     * Call when a commuter finish the journey

     * @param cardNumber read from the cardReader
     */
  public void finishJouney(String cardNumber){


      Journey journey=journeyList.get(cardNumber);
      journey.setExit(Current_station);
      journey.setEndTime(utils.currentTime());
      journey.setCost(30);

      realTimeDB_service.addJourney(cardNumber,journey);
      journey.save();
      journeyList.remove(cardNumber);
  }

    public static HashMap<String, Journey> getJourneyList() {
        return journeyList;
    }

    public static void setJourneyList(HashMap<String, Journey> journeyList) {
        JourneyService.journeyList = journeyList;
    }

    /**

     * Add item to journeyList
     * @param journey item to add to the list
     */
  private void addToList(Journey journey){}

  public Boolean ListContains(String cardNumber){

      return journeyList.containsKey(cardNumber);

  }


    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
