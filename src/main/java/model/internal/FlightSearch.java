package model.internal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FlightSearch {
    private String origin;
    private String destination;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer dayQuantityMin;
    private Integer dayQuantityMax;
    private List<GDS> gds;
    private Integer everyHours = 4;
    private Double alertPrice;

    public FlightSearch(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax, Double alertPrice){
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dayQuantityMin = dayQuantityMin;
        this.dayQuantityMax = dayQuantityMax;
        this.gds = Arrays.asList(GDS.amadeus(), GDS.sabre(), GDS.worldspan());
        this.alertPrice = alertPrice;
    }

    public FlightSearch(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax, Double alertPrice, List<GDS> gds){
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dayQuantityMin = dayQuantityMin;
        this.dayQuantityMax = dayQuantityMax;
        this.gds = gds;
        this.alertPrice = alertPrice;
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

    public Integer getDayQuantityMin() {
        return dayQuantityMin;
    }

    public Integer getDayQuantityMax() {
        return dayQuantityMax;
    }

    public List<GDS> getGds() {
        return gds;
    }

    public Integer getEveryHours() {
        return everyHours;
    }

    public void setEveryHours(Integer everyHours) {
        this.everyHours = everyHours;
    }

    public Double getAlertPrice() {
        return alertPrice;
    }

    public String getDescription(){
        return origin + "->" + destination + "::" + dateFrom + "->" + dateTo + "::" + dayQuantityMin + "->" + dayQuantityMax + "(" + alertPrice + ")";
    }
}
