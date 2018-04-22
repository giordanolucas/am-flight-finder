package scrapper;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import model.internal.FlightQuery;
import model.internal.FlightResult;
import model.internal.FlightSearch;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Scrapper {
    private static OkHttpClient client;

    private static void configureHttpClient() {
        client = new OkHttpClient();
        client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(80, TimeUnit.SECONDS);    // socket timeout
    }

    public static void main (String [] arguments) throws ExecutionException, InterruptedException {
        configureHttpClient();

        List<FlightSearch> searches = new ArrayList<>();
        searches.add(new FlightSearch("BUE", "TYO", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));
        searches.add(new FlightSearch("BUE", "BKK", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));
        searches.add(new FlightSearch("BUE", "MAD", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));
        searches.add(new FlightSearch("BUE", "BCN", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));
        searches.add(new FlightSearch("BUE", "KUL", LocalDate.of(2018, 10, 5), LocalDate.of(2019, 4, 1), 15, 19));

        searches.parallelStream().forEach(flightSearch -> runSearch(flightSearch) );
    }

    private static void runSearch(FlightSearch flightSearch) {
        DatabaseResultHandler databaseResultHandler = new DatabaseResultHandler();

        List<FlightQuery> flightQueryList = QueryGenerator.generateQueries(flightSearch);

        System.out.println("Query count: " + flightQueryList.size());

        flightQueryList.parallelStream().forEach(i -> {
            try {
                List<FlightResult> flightResults = scrap(i);
                databaseResultHandler.saveQuery(i);
                databaseResultHandler.addResult(flightResults);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static List<FlightResult> scrap(FlightQuery flightQuery) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url("https://almundo.com.ar/flights/async/itineraries?adults=1&date=" + flightQuery.getDateFrom() + "," + flightQuery.getDateTo() + "&from=" + flightQuery.getOrigin() + "," + flightQuery.getDestination() + "&limit=5&offset=0&sortBy=PRICE&to=" + flightQuery.getDestination() + "," + flightQuery.getOrigin())
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "10c0de7c-a4c5-2be3-55a9-89a660bee80b");

        if(flightQuery.getGDS() != null && !flightQuery.getGDS().getCode().trim().equalsIgnoreCase("")){
            builder.addHeader("X-AM-PROVIDER", flightQuery.getGDS().getCode());
        }

        Request request = builder.build();

        return getScrappedFlights(flightQuery, request);
    }

    private static List<FlightResult> getScrappedFlights(FlightQuery flightQuery, Request request) throws IOException {
        Response response = null;
        try{
            response = client.newCall(request).execute();

            model.am.FlightResult flightResult;
            try{
                Gson gson = new Gson();
                flightResult = gson.fromJson(response.body().charStream(), model.am.FlightResult.class);
            }
            catch (Exception e){
                System.out.println("Error getting object from JSON: " + flightQuery.printInfo());
                return new ArrayList<>();
            }

            try{
                return flightResult.getResults().getClusters()
                        .stream().map(x -> new FlightResult(flightQuery, x)).collect(Collectors.toList());
            }
            catch (NullPointerException e){
                System.out.println("NPE for " + flightQuery.printInfo() + ". Date is probably out of range.");
                return new ArrayList<>();
            }
        }
        catch (SocketTimeoutException e){
            return  getScrappedFlights(flightQuery, request);
        }
        finally {
            if(response != null && response.body() != null){
                response.body().close();
            }
        }
    }

}
