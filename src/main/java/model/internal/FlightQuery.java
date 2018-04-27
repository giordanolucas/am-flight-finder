package model.internal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static util.DateUtils.getAmFormatedDate;

public class FlightQuery {
    private Integer idFlightQuery;
    private GDS gds;
    private String origin;
    private String destination;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDateTime queryDate;

    public FlightQuery(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, GDS gds) {
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.gds = gds;
    }

    public FlightQuery(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, GDS gds, Integer idFlightQuery, LocalDateTime queryDate) {
        this.origin = origin;
        this.destination = destination;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.gds = gds;
        this.idFlightQuery = idFlightQuery;
        this.queryDate = queryDate;
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
        return getAmFormatedDate(dateTo);
    }

    public Long dayQuantity() {
        return ChronoUnit.DAYS.between(dateFrom, dateTo);
    }

    public GDS getGDS() {
        return gds;
    }

    public Integer getIdFlightQuery() {
        return idFlightQuery;
    }

    public void setIdFlightQuery(Integer id) {
        this.idFlightQuery = id;
    }

    public LocalDateTime getQueryDate() {
        return queryDate;
    }

    public String printInfo() {
        return origin + " -> " + destination + ": " + getDateFromString() + " -> " + getDateToString() + " (" + dayQuantity() + " days)" + (gds != null ? " [" + gds.getCode() + "]" : " [DEF]");
    }
}
