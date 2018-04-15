package scrapper;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import model.almundo.FlightResult;
import model.internal.FlightInfo;
import model.internal.ScrappedFlight;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Scrapper {
    private static OkHttpClient client = new OkHttpClient();

    public static void main ( String [] arguments ) throws IOException {
        client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(60, TimeUnit.SECONDS);    // socket timeout

        int range = 12;
        int maxMonthDay = 30;

        List<ScrappedFlight> allResults = new ArrayList<>();

        for(int i = 1; i <= maxMonthDay; i++){
            if(i + range > maxMonthDay){
                break;
            }

            String min = i < 10 ? "0"+i : ""+i;
            String max = (i + range) < 10 ? "0"+(i + range) : ""+(i + range);

            FlightInfo flightInfo = new FlightInfo("BUE", "BKK", "2018-05-" + min, "2018-05-" + max);
            allResults.addAll(scrap(flightInfo));
        }

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
