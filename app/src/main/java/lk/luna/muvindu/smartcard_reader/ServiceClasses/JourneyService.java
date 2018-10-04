package lk.luna.muvindu.smartcard_reader.ServiceClasses;

import java.util.HashMap;

import lk.luna.muvindu.smartcard_reader.Models.Journey;
import lk.luna.muvindu.smartcard_reader.Models.Route;

public class JourneyService {

  private static HashMap<String, Journey> journeyList=new HashMap<>();
  private Route route;

    /**
     * Call when a commuter getOn the bus
     * @param cardNumber read from the cardReader
     */
  public void newJourney(String cardNumber){

  }



    /**
     * Call when a commuter finish the journey

     * @param cardNumber read from the cardReader
     */
  public void finishJouney(String cardNumber){}

    /**
     * Add item to journeyList
     * @param journey item to add to the list
     */
  private void addToList(Journey journey){}

    /**
     * Call when a commuter getOn the bus
     * @param cardNumber item to search in the list
     */
  private Journey findInList(String cardNumber){ return null;}


    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
