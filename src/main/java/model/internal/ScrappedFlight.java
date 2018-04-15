package model.internal;

import model.almundo.Choice;
import model.almundo.Cluster;

import java.util.LinkedList;
import java.util.List;

public class ScrappedFlight {

    private FlightInfo flightInfo;
    private Double price;
    private String airline;
    private List<TimeCombination> timeCombinations = new LinkedList<>();

    public ScrappedFlight(FlightInfo flightInfo, Cluster cluster){
        this.flightInfo = flightInfo;
        this.price = cluster.getPrice().getTotal();
        this.airline = cluster.getSegments().get(0).getChoices().get(0).getLegs().get(0).getMarketingCarrier().getName();


        List<Choice> firstFlightChoices = cluster.getSegments().get(0).getChoices();
        List<Choice> secondFlightChoices = cluster.getSegments().get(1).getChoices();

        for(Choice choiceFirstFlight : firstFlightChoices){
            FlightTimes time1 = new FlightTimes(choiceFirstFlight.getDepartureDate(),
                                                choiceFirstFlight.getDepartureTime(),
                                                choiceFirstFlight.getArrivalDate(),
                                                choiceFirstFlight.getArrivalDate(),
                                                choiceFirstFlight.getFlightDuration());

            for(Choice choiceSecondFlight : secondFlightChoices){
                FlightTimes time2 = new FlightTimes(choiceSecondFlight.getDepartureDate(),
                                                    choiceSecondFlight.getDepartureTime(),
                                                    choiceSecondFlight.getArrivalDate(),
                                                    choiceSecondFlight.getArrivalDate(),
                                                    choiceSecondFlight.getFlightDuration());

                timeCombinations.add(new TimeCombination(time1, time2));
            }
        }
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public Double getPrice() {
        return price;
    }

    public String getAirline() {
        return airline;
    }

    public List<TimeCombination> getTimeCombinations() {
        return timeCombinations;
    }
}
