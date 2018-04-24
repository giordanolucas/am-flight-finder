package scrapper;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.internal.FlightSearch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Program {
    private static final String CONFIG_FILE = "search-parameters.json";

    public static void main(String[] arguments) throws ExecutionException, InterruptedException {

        List<FlightSearch> searches = getConfigFileSearches(CONFIG_FILE);

        if (searches.isEmpty()) {
            System.out.println("No search params found. Falling back to default params.");
            searches = getDefaultSearches();
        } else {
            System.out.println(searches.size() + " searches loaded");
        }

        SearchSchedulerRunner schedulerRunner = new SearchSchedulerRunner(searches);
        schedulerRunner.run();
    }

    private static List<FlightSearch> getConfigFileSearches(String relativePath) {
        Path path = Paths.get(relativePath);
        System.out.println("Trying to load search parameters from " + path.toAbsolutePath().toString());

        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(relativePath));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return new LinkedList<>();
        }

        try {
            Gson gson = new Gson();
            FlightSearch[] config = gson.fromJson(reader, FlightSearch[].class);
            return Arrays.asList(config);
        } catch (Exception e) {
            System.err.println("Could not parse file contents");
        }

        return new LinkedList<>();
    }

    private static List<FlightSearch> getDefaultSearches() {
        System.out.println("Using default search parameters");
        List<FlightSearch> searches = new ArrayList<>();
        searches.add(new FlightSearch("BUE", "TYO", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 15, 19, 20000d));
        searches.add(new FlightSearch("BUE", "BKK", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 15, 19, 20000d));
        searches.add(new FlightSearch("BUE", "MAD", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 15, 19, 19000d));
        searches.add(new FlightSearch("BUE", "BCN", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 15, 19, 19000d));
        searches.add(new FlightSearch("BUE", "KUL", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 15, 19, 20000d));
        searches.add(new FlightSearch("BUE", "HAV", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 13, 17, 15000d));
        searches.add(new FlightSearch("BUE", "VRA", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 13, 17, 15000d));
        searches.add(new FlightSearch("BUE", "CUN", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 13, 17, 15000d));
        searches.add(new FlightSearch("BUE", "KIN", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 13, 17, 15000d));
        searches.add(new FlightSearch("BUE", "RUB", LocalDate.of(2018, 10, 1), LocalDate.of(2019, 4, 1), 13, 17, 15000d));
        return searches;
    }
}