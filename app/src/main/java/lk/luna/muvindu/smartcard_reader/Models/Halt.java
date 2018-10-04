package lk.luna.muvindu.smartcard_reader.Models;

public class Halt {
    private String stationId;
    private String name;
    private String location;



    private int index;


    /**
     * Getters and Setter for Atriibutes
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}
