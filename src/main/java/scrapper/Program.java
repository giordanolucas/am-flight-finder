package scrapper;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.internal.FlightSearch;
import model.internal.dto.JsonFlightSearch;
import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class Program {
    private static String configFilePath = "search-parameters.json";
    private static boolean shouldResume = false;

    public static void main(String[] arguments) {
        loadArgs(arguments);

        List<FlightSearch> searches = getConfigFileSearches(configFilePath);

        if (searches.isEmpty()) {
            System.out.println("No search params found. Falling back to default params.");
            searches = getDefaultSearches();
        } else {
            System.out.println(searches.size() + " searches loaded");
        }

        SearchSchedulerRunner schedulerRunner = new SearchSchedulerRunner(searches);
        schedulerRunner.run();
    }

    private static void loadArgs(String[] args){
        Options options = new Options();

        Option input = new Option("i", "input", true, "Search parameters file path");
        input.setRequired(false);
        options.addOption(input);

        Option resume = new Option("r", "resume", false, "Resumes a previous execution");
        resume.setRequired(false);
        options.addOption(resume);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        if(cmd.hasOption("input")){
            configFilePath = cmd.getOptionValue("input");
        }
        shouldResume = cmd.hasOption("resume");
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
            JsonFlightSearch[] config = gson.fromJson(reader, JsonFlightSearch[].class);
            return Arrays.stream(config).map(JsonFlightSearch::getFlightSearch).collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Could not parse file contents");
            System.err.println(e.getMessage());
        }

        return new LinkedList<>();
    }

    private static List<FlightSearch> getDefaultSearches() {
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