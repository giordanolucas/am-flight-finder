package scrapper;

import model.internal.FlightQuery;
import model.internal.GDS;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SearchGenerator {

    public static List<FlightQuery> generateSearchs(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantity, GDS gds){
        List<FlightQuery> flightQueryList = new LinkedList<>();

        while (dateFrom.plusDays(dayQuantity).isBefore(dateTo)) {
            flightQueryList.add(new FlightQuery(origin, destination, dateFrom, dateFrom.plusDays(dayQuantity), gds));

            dateFrom = dateFrom.plusDays(1);
        }

        return flightQueryList;
    }

    public static List<FlightQuery> generateSearchs(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax, GDS gds){
        List<FlightQuery> flightQueryList = new LinkedList<>();

        for(int i = dayQuantityMin; i <= dayQuantityMax; i++){
            flightQueryList.addAll(generateSearchs(origin, destination, dateFrom, dateTo, i, gds));
        }

        return flightQueryList;
    }

    public static List<FlightQuery> generateSearchs(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax, List<GDS> gdss){
        List<FlightQuery> flightQueryList = new LinkedList<>();

        for(GDS gds : gdss){
            flightQueryList.addAll(generateSearchs(origin, destination, dateFrom, dateTo, dayQuantityMin, dayQuantityMax, gds));
        }

        return flightQueryList;
    }
}
