package scrapper;

import model.internal.FlightQuery;
import model.internal.FlightSearch;
import model.internal.GDS;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class QueryGenerator {

    public static List<FlightQuery> generateQuery(String origin, String destination, LocalDate dateFrom, LocalDate dateTo, Integer dayQuantity, GDS gds) {
        List<FlightQuery> flightQueryList = new LinkedList<>();

        while (dateFrom.plusDays(dayQuantity).isBefore(dateTo)) {
            flightQueryList.add(new FlightQuery(origin, destination, dateFrom, dateFrom.plusDays(dayQuantity), gds));

            dateFrom = dateFrom.plusDays(1);
        }

        return flightQueryList;
    }

    public static List<FlightQuery> generateQueries(FlightSearch search) {
        List<FlightQuery> flightQueryList = new LinkedList<>();

        for (int i = search.getDayQuantityMin(); i <= search.getDayQuantityMax(); i++) {
            if (search.getGds() != null && search.getGds().size() > 0) {
                for (GDS gds : search.getGds()) {
                    flightQueryList.addAll(generateQuery(search.getOrigin(), search.getDestination(), search.getDateFrom(), search.getDateTo(), i, gds));
                }
            } else {
                flightQueryList.addAll(generateQuery(search.getOrigin(), search.getDestination(), search.getDateFrom(), search.getDateTo(), i, null));
            }

        }

        return flightQueryList;
    }
}
