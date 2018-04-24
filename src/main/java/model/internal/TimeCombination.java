package model.internal;

import java.util.List;

public class TimeCombination {
    private List<FlightTimes> flightTimes;

    public TimeCombination(List<FlightTimes> flightTimes) {
        this.flightTimes = flightTimes;
    }

    public List<FlightTimes> getFlightTimes() {
        return flightTimes;
    }

    public void setFlightTimes(List<FlightTimes> flightTimes) {
        this.flightTimes = flightTimes;
    }
}
