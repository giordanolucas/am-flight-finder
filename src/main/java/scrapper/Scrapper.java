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
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Scrapper {
    private static OkHttpClient client;

    private static void configureClient() {
        client = new OkHttpClient();
        client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(80, TimeUnit.SECONDS);    // socket timeout
    }

    public static void main (String [] arguments) throws IOException, ExecutionException, InterruptedException {
        configureClient();

        String origin = "BUE";
        String destination = "TYO";
        LocalDate dateFrom = LocalDate.of(2018, 9, 20);
        LocalDate dateTo = LocalDate.of(2018, 10, 10);
        //LocalDate dateTo = LocalDate.of(2019, 2, 1);
        Integer dayQuantityMin = 15;
        Integer dayQuantityMax = 19;
        List<String> providers = Arrays.asList("AMA", "WOR", "SAB");

        ResultHandler resultHandler = new FileResultHandler("flightResults.txt",23000d);

        List<FlightInfo> flightInfoList = SearchGenerator.generateSearchs(origin, destination, dateFrom, dateTo, dayQuantityMin, dayQuantityMax, providers);
        System.out.println("Query count: " + flightInfoList.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(200);
        forkJoinPool.submit(() ->
            flightInfoList.parallelStream().forEach(i -> {
                try {
                    resultHandler.addResult(scrap(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            })
        ).get();


        System.out.println("All queries finished, " + resultHandler.getResultCount() + " results.");
        resultHandler.printResults();
    }


    public static List<ScrappedFlight> scrap(FlightInfo flightInfo) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url("https://almundo.com.ar/flights/async/itineraries?adults=1&date=" + flightInfo.getDateFrom() + "," + flightInfo.getDateTo() + "&from=" + flightInfo.getOrigin() + "," + flightInfo.getDestination() + "&sortBy=PRICE&to=" + flightInfo.getDestination() + "," + flightInfo.getOrigin())
                .get()
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "10c0de7c-a4c5-2be3-55a9-89a660bee80b");

        if(flightInfo.getProvider() != null && !flightInfo.getProvider().trim().equalsIgnoreCase("")){
            builder.addHeader("X-AM-PROVIDER", flightInfo.getProvider());
        }

        Request request = builder.build();

        return getScrappedFlights(flightInfo, request);
    }

    private static List<ScrappedFlight> getScrappedFlights(FlightInfo flightInfo, Request request) throws IOException {
        Response response = null;
        try{
            response = client.newCall(request).execute();

            FlightResult flightResult;
            try{
                Gson gson = new Gson();
                flightResult = gson.fromJson(response.body().charStream(), FlightResult.class);
            }
            catch (Exception e){
                System.out.println("Error getting object from JSON: " + flightInfo.printInfo());
                return new ArrayList<>();
            }

            try{
                return flightResult.getResults().getClusters()
                        .stream().map(x -> new ScrappedFlight(flightInfo, x)).collect(Collectors.toList());
            }
            catch (NullPointerException e){
                System.out.println("NPE for " + flightInfo.printInfo() + ". Date is probably out of range.");
                return new ArrayList<>();
            }
        }
        catch (SocketTimeoutException e){
            return  getScrappedFlights(flightInfo, request);
        }
        finally {
            if(response != null && response.body() != null){
                response.body().close();
            }
        }
    }
}
