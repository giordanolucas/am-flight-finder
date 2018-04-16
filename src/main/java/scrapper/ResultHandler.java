package scrapper;

import model.internal.ScrappedFlight;

import java.util.ArrayList;
import java.util.List;

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
            System.out.println("New result: " + flight.getPrice() + " :: " + flight.getFlightInfo().printInfo());

            if(flight.getPrice() < alertPrice){
                System.err.println("ALERT!!!! " + flight.getPrice() + " :: " + flight.getFlightInfo().printInfo());
            }
        }
    }

    public void printResults(){
        allResults.stream()
                .sorted((x, y) -> (x.getPrice().equals(y.getPrice())) ? 0 : (x.getPrice() > y.getPrice() ? 1 : -1))
                .map(x -> x.getPrice() + " :: " + x.getFlightInfo().printInfo())
                .forEach(System.out::println);
    }

    public Integer getResultCount(){
        return allResults.size();
    }
}
