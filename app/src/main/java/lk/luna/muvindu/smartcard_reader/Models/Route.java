package lk.luna.muvindu.smartcard_reader.Models;

public class Route {
    private int routeId;
    private String routeName;
    private double cost;

    public void newJourney(String cardNumber){}
    public void finishJourney(String cardNumber){}
    public void addToList(Journey journey){}

    /**
     * Getters and setters for the attributes
     * @return
     */

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
