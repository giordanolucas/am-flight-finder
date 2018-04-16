package scrapper;

import model.internal.FlightInfo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SearchGenerator {

    public static List<FlightInfo> generateSearchs(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantity, String provider){
        List<FlightInfo> flightInfoList = new LinkedList<>();

        while (dateFrom.plusDays(dayQuantity).isBefore(dateTo)) {
            flightInfoList.add(new FlightInfo(origin, destination, dateFrom, dateFrom.plusDays(dayQuantity), provider));

            dateFrom = dateFrom.plusDays(1);
        }

        return flightInfoList;
    }

    public static List<FlightInfo> generateSearchs(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax, String provider){
        List<FlightInfo> flightInfoList = new LinkedList<>();

        for(int i = dayQuantityMin; i <= dayQuantityMax; i++){
            flightInfoList.addAll(generateSearchs(origin, destination, dateFrom, dateTo, i, provider));
        }

        return flightInfoList;
    }

    public static List<FlightInfo> generateSearchs(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax){
        List<FlightInfo> flightInfoList = new LinkedList<>();

        for(int i = dayQuantityMin; i <= dayQuantityMax; i++){
            flightInfoList.addAll(generateSearchs(origin, destination, dateFrom, dateTo, i, ""));
        }

        return flightInfoList;
    }

    public static List<FlightInfo> generateSearchs(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax, List<String> providers){
        List<FlightInfo> flightInfoList = new LinkedList<>();

        for(String provider : providers){
            flightInfoList.addAll(generateSearchs(origin, destination, dateFrom, dateTo, dayQuantityMin, dayQuantityMax, provider));
        }

        return flightInfoList;
    }

    public static List<FlightInfo> generateSearchs(String origin, List<String> destinations, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantityMin, Integer dayQuantityMax, List<String> providers){
        List<FlightInfo> flightInfoList = new LinkedList<>();

        for(String destination : destinations){
            flightInfoList.addAll(generateSearchs(origin, destination, dateFrom, dateTo, dayQuantityMin, dayQuantityMax, providers));
        }

        return flightInfoList;
    }
}
