package scrapper;

import model.internal.ScrappedFlight;

import java.util.ArrayList;
import java.util.List;

public class ResultHandler {
    private static List<ScrappedFlight> allResults = new ArrayList<>();

    public static void addResult(List<ScrappedFlight> result){
        allResults.addAll(result);

        for(ScrappedFlight flight : result){
            System.out.println("New result: " + flight.getPrice() + " :: " + flight.getFlightInfo().printInfo());
        }
    }

    public static void printResults(){
        allResults.stream()
                .sorted((x, y) -> (x.getPrice().equals(y.getPrice())) ? 0 : (x.getPrice() > y.getPrice() ? 1 : -1))
                .map(x -> x.getPrice() + " :: " + x.getFlightInfo().printInfo())
                .forEach(System.out::println);
    }

    public static Integer getResultCount(){
        return allResults.size();
    }
}
