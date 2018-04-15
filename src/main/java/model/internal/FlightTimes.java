package model.internal;

public class FlightTimes {
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String duration;

    public FlightTimes(String departureDate, String departureTime, String arrivalDate, String arrivalTime, String duration){
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }


    public String getArrivalDate() {
        return arrivalDate;
    }


    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDuration() {
        return duration;
    }
}
