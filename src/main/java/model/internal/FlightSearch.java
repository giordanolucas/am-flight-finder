package model.internal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FlightSearch {
    private String origin = "BUE";
    private String destination = "TYO";
    private LocalDate dateFrom = LocalDate.of(2018, 10, 5);
    private LocalDate dateTo = LocalDate.of(2019, 4, 1);
    private Integer dayQuantityMin = 16;
    private Integer dayQuantityMax = 19;
    private List<GDS> gds = Arrays.asList(GDS.amadeus(), GDS.sabre(), GDS.worldspan());

    public FlightSearch(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax){
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dayQuantityMin = dayQuantityMin;
        this.dayQuantityMax = dayQuantityMax;
        this.gds = Arrays.asList(GDS.amadeus(), GDS.sabre(), GDS.worldspan());
    }

    public FlightSearch(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax, List<GDS> gds){
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.dayQuantityMin = dayQuantityMin;
        this.dayQuantityMax = dayQuantityMax;
        this.gds = gds;
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
}
