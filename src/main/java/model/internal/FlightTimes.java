package model.internal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FlightTimes {
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private String duration;

    public FlightTimes(String departureDateString, String departureTimeString, String arrivalDateString, String arrivalTimeString, String duration) {

        LocalDate departureDate = LocalDate.parse(departureDateString, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate arrivalDate = LocalDate.parse(arrivalDateString, DateTimeFormatter.ISO_LOCAL_DATE);

        LocalTime departureTime = LocalTime.parse(departureTimeString);
        LocalTime arrivalTime = LocalTime.parse(arrivalTimeString);

        this.departure = departureDate.atTime(departureTime);
        this.arrival = arrivalDate.atTime(arrivalTime);

        this.duration = duration;
    }

    public FlightTimes(Integer idFlightTimes, LocalDateTime departure, LocalDateTime arrival, String duration) {
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
    }

    public String getDepartureDateTimeString() {
        return departure.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getArrivalDateTimeString() {
        return arrival.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public String getDuration() {
        return duration;
    }

    public Integer getDurationInteger() {
        String[] split = duration.split(":");

        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}