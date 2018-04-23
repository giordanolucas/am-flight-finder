package scrapper;

import model.internal.FlightSearch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Program {
    public static void main (String [] arguments) throws ExecutionException, InterruptedException {
        List<FlightSearch> searches = new ArrayList<>();
        searches.add(new FlightSearch("BUE", "TYO", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));
        searches.add(new FlightSearch("BUE", "BKK", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));
        searches.add(new FlightSearch("BUE", "MAD", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));
        searches.add(new FlightSearch("BUE", "BCN", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));
        searches.add(new FlightSearch("BUE", "KUL", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));

        SearchSchedulerRunner schedulerRunner = new SearchSchedulerRunner(searches);
        schedulerRunner.run();
    }
}
