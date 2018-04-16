package scrapper;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import model.am.FlightResult;
import model.internal.FlightInfo;
import model.internal.ScrappedFlight;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Scrapper {
    private static OkHttpClient client;

    private static void configureClient() {
        client = new OkHttpClient();
        client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(60, TimeUnit.SECONDS);    // socket timeout
    }

    public static void main (String [] arguments) throws IOException, ExecutionException, InterruptedException {
        configureClient();

        String origin = "BUE";
        String destination = "BKK";
        LocalDate dateFrom = LocalDate.of(2018, 9, 1);
        LocalDate dateTo = LocalDate.of(2018, 10, 25);
        Integer dayQuantity = 10;

        List<FlightInfo> flightInfoList = SearchGenerator.generateSearchs(origin, destination, dateFrom, dateTo, dayQuantity);
        System.out.println("Query count: " + flightInfoList.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(1);
        List<ScrappedFlight> allResults = forkJoinPool.submit(() ->
                flightInfoList.parallelStream().flatMap(i -> {
                    try {
                        System.out.println("Current query: " + i.printInfo());
                        return scrap(i).stream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
        ).get()
         .filter(Objects::nonNull)
         .collect(Collectors.toList());


        System.out.println("All queries finished, " + allResults.size() + " results.");
        System.out.print(allResults.toString());
    }

    public static List<ScrappedFlight> scrap(FlightInfo flightInfo) throws IOException {
        Request request = new Request.Builder()
                .url("https://almundo.com.ar/flights/async/itineraries?adults=1&date=" + flightInfo.getDateFrom() + "," + flightInfo.getDateTo() + "&from=" + flightInfo.getOrigin() + "," + flightInfo.getDestination() + "&sortBy=PRICE&to=" + flightInfo.getDestination() + "," + flightInfo.getOrigin())
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "10c0de7c-a4c5-2be3-55a9-89a660bee80b")
                .build();

        return getScrappedFlights(flightInfo, request);
    }

    private static List<ScrappedFlight> getScrappedFlights(FlightInfo flightInfo, Request request) throws IOException {
        Response response;
        try{
            response = client.newCall(request).execute();
        }
        catch (SocketTimeoutException e){
            return  getScrappedFlights(flightInfo, request);
        }

        Gson gson = new Gson();
        FlightResult flightResult = gson.fromJson(response.body().charStream(), FlightResult.class);

        return flightResult.getResults().getClusters()
                .stream().map(x -> new ScrappedFlight(flightInfo, x)).collect(Collectors.toList());
    }
}
