package scrapper;

import model.internal.ScrappedFlight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultHandler {
    protected List<ScrappedFlight> allResults = new ArrayList<>();
    protected double alertPrice = 0;

    public ResultHandler(){}

    public ResultHandler(Double alertPrice){
        this.alertPrice = alertPrice;
    }

    public void addResult(List<ScrappedFlight> result){
        allResults.addAll(result);

        for(ScrappedFlight flight : result){
            System.out.println("New result: " + getFlightDataString(flight));

            if(flight.getPrice() < alertPrice){
                System.err.println("ALERT!!!! " + getFlightDataString(flight));
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

    protected static String getFlightDataString(ScrappedFlight flight){
        return flight.getPriceString() + " :: " + flight.getFlightInfo().printInfo() + " :: " + flight.getAirline();
    }
}
