package scrapper;

import model.internal.FlightQuery;
import model.internal.FlightResult;
import model.internal.FlightSearch;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SearchSchedulerRunner {

    private List<FlightSearch> searches;
    private List<FlightSearch> running = new ArrayList<>();
    private HashMap<FlightSearch, LocalDateTime> lastRun = new HashMap<>();

    private DatabaseResultHandler databaseResultHandler = new DatabaseResultHandler();

    public SearchSchedulerRunner(List<FlightSearch> searches) {
        this.searches = searches;
    }

    public void run() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> startSearches(), 0, 1, TimeUnit.MINUTES);
    }

    private void startSearches() {
        for (FlightSearch search : searches) {
            if (!running.contains(search)) {
                if (lastRun.containsKey(search)) {
                    if (lastRun.get(search).plusHours(search.getEveryHours()).isBefore(LocalDateTime.now())) {
                        Thread thread = new Thread(() -> runSearch(search));
                        thread.start();
                    }
                } else {
                    Thread thread = new Thread(() -> runSearch(search));
                    thread.start();
                }
            }
        }
    }

    private void runSearch(FlightSearch flightSearch) {
        running.add(flightSearch);
        lastRun.put(flightSearch, LocalDateTime.now());

        System.out.println("Starting search: " + flightSearch.getDescription() + " - " + LocalDateTime.now());

        List<FlightQuery> flightQueryList = QueryGenerator.generateQueries(flightSearch);

        flightQueryList.parallelStream().forEach(flightQuery -> {
            try {
                List<FlightResult> flightResults = Scrapper.scrap(flightQuery);
                databaseResultHandler.saveQuery(flightQuery);

                if(flightResults != null && flightResults.size() > 0){
                    databaseResultHandler.addResult(flightResults);
                    MailResultHandler.notify(flightSearch, flightResults);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        running.remove(flightSearch);
        System.out.println("Search ended: " + flightSearch.getDescription() + " - " + LocalDateTime.now());
    }
}
