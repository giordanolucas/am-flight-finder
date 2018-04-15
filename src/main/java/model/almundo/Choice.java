
package model.almundo;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Choice {

    @SerializedName("arrival_date")
    private String mArrivalDate;
    @SerializedName("arrival_time")
    private String mArrivalTime;
    @SerializedName("baggage")
    private Baggage mBaggage;
    @SerializedName("baggage_allowed")
    private Boolean mBaggageAllowed;
    @SerializedName("departure_date")
    private String mDepartureDate;
    @SerializedName("departure_time")
    private String mDepartureTime;
    @SerializedName("destination")
    private Destination mDestination;
    @SerializedName("flight_duration")
    private String mFlightDuration;
    @SerializedName("id")
    private String mId;
    @SerializedName("legs")
    private List<Leg> mLegs;
    @SerializedName("origin")
    private Origin mOrigin;
    @SerializedName("time_between_legs")
    private List<String> mTimeBetweenLegs;

    public String getArrivalDate() {
        return mArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        mArrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return mArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        mArrivalTime = arrivalTime;
    }

    public Baggage getBaggage() {
        return mBaggage;
    }

    public void setBaggage(Baggage baggage) {
        mBaggage = baggage;
    }

    public Boolean getBaggageAllowed() {
        return mBaggageAllowed;
    }

    public void setBaggageAllowed(Boolean baggageAllowed) {
        mBaggageAllowed = baggageAllowed;
    }

    public String getDepartureDate() {
        return mDepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        mDepartureDate = departureDate;
    }

    public String getDepartureTime() {
        return mDepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        mDepartureTime = departureTime;
    }

    public Destination getDestination() {
        return mDestination;
    }

    public void setDestination(Destination destination) {
        mDestination = destination;
    }

    public String getFlightDuration() {
        return mFlightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        mFlightDuration = flightDuration;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public List<Leg> getLegs() {
        return mLegs;
    }

    public void setLegs(List<Leg> legs) {
        mLegs = legs;
    }

    public Origin getOrigin() {
        return mOrigin;
    }

    public void setOrigin(Origin origin) {
        mOrigin = origin;
    }

    public List<String> getTimeBetweenLegs() {
        return mTimeBetweenLegs;
    }

    public void setTimeBetweenLegs(List<String> timeBetweenLegs) {
        mTimeBetweenLegs = timeBetweenLegs;
    }

}
