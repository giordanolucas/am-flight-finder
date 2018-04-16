package scrapper;

import model.internal.FlightInfo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static util.DateUtils.getAmFormatedDate;

public class SearchGenerator {

    public static List<FlightInfo> generateSearchs(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantity){
        List<FlightInfo> flightInfoList = new LinkedList<>();

        while (dateFrom.plusDays(dayQuantity).isBefore(dateTo)) {
            flightInfoList.add(new FlightInfo(origin, destination, getAmFormatedDate(dateFrom), getAmFormatedDate(dateFrom.plusDays(dayQuantity))));

            dateFrom = dateFrom.plusDays(1);
        }

        return flightInfoList;
    }
}
