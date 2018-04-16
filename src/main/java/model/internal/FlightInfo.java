package model.internal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static util.DateUtils.getAmFormatedDate;

public class FlightInfo {
    private String provider;
    private String origin;
    private String destination;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public FlightInfo(String origin, String destination, LocalDate dateFrom, LocalDate dateTo){
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public FlightInfo(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, String provider){
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.provider = provider;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public String getDateFromString() {
        return getAmFormatedDate(dateFrom);
    }

    public String getDateToString() {
        return getAmFormatedDate(dateFrom);
    }

    public Long dayQuantity(){
        return ChronoUnit.DAYS.between(dateFrom, dateTo);
    }

    public String getProvider() {
        return provider;
    }

    public String printInfo() {
        return origin + " -> " + destination + ": " + getDateFromString() + " -> " + getDateFromString() + " (" + dayQuantity() + " days)" + (provider != null ? " [" + provider + "]" : "");
    }
}
