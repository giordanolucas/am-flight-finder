package scrapper;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import model.internal.FlightQuery;
import model.internal.FlightResult;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Scrapper {
    private static final String RESULT_COUNT_PER_SEARCH = "4";

    private static OkHttpClient client = new OkHttpClient();

    static {
        client.setConnectTimeout(20, TimeUnit.SECONDS);
        client.setReadTimeout(90, TimeUnit.SECONDS);
    }

    public static List<FlightResult> scrap(FlightQuery flightQuery) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url("https://almundo.com.ar/flights/async/itineraries?adults=1&date=" + flightQuery.getDateFrom() + "," + flightQuery.getDateTo() + "&from=" + flightQuery.getOrigin() + "," + flightQuery.getDestination() + "&limit=" + RESULT_COUNT_PER_SEARCH + "&offset=0&sortBy=PRICE&to=" + flightQuery.getDestination() + "," + flightQuery.getOrigin())
                .get();

        if (flightQuery.getGDS() != null && StringUtils.isNotBlank(flightQuery.getGDS().getCode())) {
            builder.addHeader("X-AM-PROVIDER", flightQuery.getGDS().getCode());
        }

        Request request = builder.build();

        return getScrappedFlights(flightQuery, request);
    }

    private static List<FlightResult> getScrappedFlights(FlightQuery flightQuery, Request request) throws IOException {
        Response response = null;
        try {
            response = client.newCall(request).execute();

            model.am.FlightResult flightResult;
            try {
                Gson gson = new Gson();
                flightResult = gson.fromJson(response.body().charStream(), model.am.FlightResult.class);
            } catch (Exception e) {
                System.out.println("Error getting object from JSON: " + flightQuery.printInfo());
                return new ArrayList<>();
            }

            try {
                return flightResult.getResults().getClusters()
                        .stream().map(x -> new FlightResult(flightQuery, x)).collect(Collectors.toList());
            } catch (NullPointerException e) {
                System.out.println("NPE for " + flightQuery.printInfo() + ". Date is probably out of range.");
                return new ArrayList<>();
            }
        } catch (SocketTimeoutException e) {
            return getScrappedFlights(flightQuery, request);
        } finally {
            if (response != null && response.body() != null) {
                response.body().close();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
