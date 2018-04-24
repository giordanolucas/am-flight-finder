package model.internal;

import com.google.gson.Gson;
import model.am.Choice;
import model.am.Cluster;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FlightResult {

    private Integer idFlightResult;
    private FlightQuery flightQuery;
    private Double price;
    private String airline;
    private List<TimeCombination> timeCombinations = new LinkedList<>();
    private LocalDateTime resultDate;

    public FlightResult(Integer idFlightResult, FlightQuery flightQuery, Double price, String airline, LocalDateTime resultDate, String flightCombinationsJson) {
        this.idFlightResult = idFlightResult;
        this.flightQuery = flightQuery;
        this.price = price;
        this.airline = airline;
        this.timeCombinations = (new Gson()).fromJson(flightCombinationsJson, this.timeCombinations.getClass());
        this.resultDate = resultDate;
    }

    public FlightResult(FlightQuery flightQuery, Cluster cluster) {
        this.flightQuery = flightQuery;
        this.price = cluster.getPrice().getTotal();
        this.airline = cluster.getSegments().get(0).getChoices().get(0).getLegs().get(0).getMarketingCarrier().getName();


        List<Choice> firstFlightChoices = cluster.getSegments().get(0).getChoices();
        List<Choice> secondFlightChoices = cluster.getSegments().get(1).getChoices();

        for (Choice choiceFirstFlight : firstFlightChoices) {
            FlightTimes time1 = new FlightTimes(choiceFirstFlight.getDepartureDate(),
                    choiceFirstFlight.getDepartureTime(),
                    choiceFirstFlight.getArrivalDate(),
                    choiceFirstFlight.getArrivalTime(),
                    choiceFirstFlight.getFlightDuration());

            for (Choice choiceSecondFlight : secondFlightChoices) {
                FlightTimes time2 = new FlightTimes(choiceSecondFlight.getDepartureDate(),
                        choiceSecondFlight.getDepartureTime(),
                        choiceSecondFlight.getArrivalDate(),
                        choiceSecondFlight.getArrivalTime(),
                        choiceSecondFlight.getFlightDuration());

                timeCombinations.add(new TimeCombination(Arrays.asList(time1, time2)));
            }
        }
    }

    public FlightQuery getFlightQuery() {
        return flightQuery;
    }

    public Double getPrice() {
        return price;
    }

    public String getPriceString() {
        DecimalFormat twoPlaces = new DecimalFormat("0.00");
        return twoPlaces.format(price);
    }

    public String getAirline() {
        return airline;
    }

    public List<TimeCombination> getTimeCombinations() {
        return timeCombinations;
    }

    public Integer getIdFlightResult() {
        return idFlightResult;
    }

    public void setIdFlightResult(Integer id) {
        this.idFlightResult = id;
    }

    public LocalDateTime getResultDate() {
        return resultDate;
    }
}
