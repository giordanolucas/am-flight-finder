package scrapper;

import model.internal.FlightResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultHandler {
    protected List<FlightResult> allResults = new ArrayList<>();
    protected double alertPrice = 0;

    public ResultHandler(){}

    public ResultHandler(Double alertPrice){
        this.alertPrice = alertPrice;
    }

    public void addResult(List<FlightResult> result){
        if(result.size() > 0){
            allResults.addAll(result);

            FlightResult firstFlight = result.get(0);

            if(firstFlight.getPrice() < alertPrice){
                System.err.println(getFlightDataString(firstFlight) + " <-- ALERT!!!");
            }
            else{
                System.out.println(getFlightDataString(firstFlight));
            }
        }
    }

    public void printResults(){
        allResults.stream()
                .filter(Objects::nonNull)
                .sorted((x, y) -> (x.getPrice().equals(y.getPrice())) ? 0 : (x.getPrice() > y.getPrice() ? 1 : -1))
                .map(ResultHandler::getFlightDataString)
                .forEach(System.out::println);
    }

    public Integer getResultCount(){
        return allResults.size();
    }

    protected static String getFlightDataString(FlightResult flight){
        return flight.getPriceString() + " :: " + flight.getFlightQuery().printInfo() + " :: " + flight.getAirline();
    }
}
