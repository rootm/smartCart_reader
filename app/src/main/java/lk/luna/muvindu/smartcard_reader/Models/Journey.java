package lk.luna.muvindu.smartcard_reader.Models;

public class Journey {

    private String onBoard;
    private String route;
    private String routeNo;
    private String onBoardTime;
    private String startTime;
    private String endTime;
    private int cost;

    public void save(){}


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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
