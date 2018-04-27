package model.internal.dto;

import model.internal.FlightSearch;
import model.internal.GDS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonFlightSearch {
    private String origin;
    private String destination;
    private String dateFrom;
    private String dateTo;
    private Integer dayQuantityMin;
    private Integer dayQuantityMax;
    private List<String> gds;
    private Integer everyHours;
    private Double alertPrice;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getDayQuantityMin() {
        return dayQuantityMin;
    }

    public void setDayQuantityMin(Integer dayQuantityMin) {
        this.dayQuantityMin = dayQuantityMin;
    }

    public Integer getDayQuantityMax() {
        return dayQuantityMax;
    }

    public void setDayQuantityMax(Integer dayQuantityMax) {
        this.dayQuantityMax = dayQuantityMax;
    }

    public List<String> getGds() {
        return gds;
    }

    public void setGds(List<String> gds) {
        this.gds = gds;
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

    public void setAlertPrice(Double alertPrice) {
        this.alertPrice = alertPrice;
    }

    public FlightSearch getFlightSearch(){
        List<GDS> gdsList = gds.stream().map(x -> GDS.getByCode(x)).filter(Objects::nonNull).collect(Collectors.toList());
        LocalDate dateFromD = LocalDate.parse(dateFrom, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate dateToD = LocalDate.parse(dateTo, DateTimeFormatter.ISO_LOCAL_DATE);

        return new FlightSearch(origin, destination, dateFromD, dateToD, dayQuantityMin, dayQuantityMax, alertPrice, gdsList);
    }
}
