package model.internal;

public class TimeCombination {
    private FlightTimes firstFlight;
    private FlightTimes secondFlight;

    public TimeCombination(FlightTimes firstFlight, FlightTimes secondFlight){
        this.firstFlight = firstFlight;
        this.secondFlight = secondFlight;
    }

    public FlightTimes getFirstFlight() {
        return firstFlight;
    }

    public FlightTimes getSecondFlight() {
        return secondFlight;
    }
}
