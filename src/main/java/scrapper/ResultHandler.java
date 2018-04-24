package scrapper;

import model.internal.FlightResult;

import java.util.List;

public class ResultHandler {
    protected double alertPrice = 0;

    public ResultHandler() {
    }

    public ResultHandler(Double alertPrice) {
        this.alertPrice = alertPrice;
    }

    protected static String getFlightDataString(FlightResult flight) {
        return flight.getPriceString() + " :: " + flight.getFlightQuery().printInfo() + " :: " + flight.getAirline();
    }

    public void addResult(List<FlightResult> result) {
        if (result.size() > 0) {

            FlightResult firstFlight = result.get(0);

            if (firstFlight.getPrice() < alertPrice) {
                System.err.println(getFlightDataString(firstFlight) + " <-- ALERT!!!");
            } else {
                System.out.println(getFlightDataString(firstFlight));
            }
        }
    }
}
