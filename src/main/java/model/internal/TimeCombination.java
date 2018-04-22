package model.internal;

import java.util.List;

public class TimeCombination {
    private List<FlightTimes> flightTimes;
    private Integer idFlightResult;
    private Integer idTimeCombination;

    public TimeCombination(List<FlightTimes> flightTimes){
        this.flightTimes = flightTimes;
    }

    public List<FlightTimes> getFlightTimes() {
        return flightTimes;
    }

    public void setIdFlightResult(Integer id){
        this.idFlightResult = id;
    }

    public Integer getIdFlightResult(){
        return idFlightResult;
    }

    public void setFlightTimes(List<FlightTimes> flightTimes) {
        this.flightTimes = flightTimes;
    }

    public Integer getIdTimeCombination() {
        return idTimeCombination;
    }

    public void setIdTimeCombination(Integer idTimeCombination) {
        this.idTimeCombination = idTimeCombination;
    }
}
