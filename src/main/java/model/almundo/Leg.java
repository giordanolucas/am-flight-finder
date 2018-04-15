
package model.almundo;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Leg {

    @SerializedName("aircraft")
    private Aircraft mAircraft;
    @SerializedName("amenities")
    private List<Amenity> mAmenities;
    @SerializedName("arrival_date")
    private String mArrivalDate;
    @SerializedName("arrival_time")
    private String mArrivalTime;
    @SerializedName("available_stock")
    private Double mAvailableStock;
    @SerializedName("baggage_allowed")
    private Boolean mBaggageAllowed;
    @SerializedName("cabin_description")
    private String mCabinDescription;
    @SerializedName("cabin_type")
    private String mCabinType;
    @SerializedName("departure_date")
    private String mDepartureDate;
    @SerializedName("departure_time")
    private String mDepartureTime;
    @SerializedName("destination")
    private Destination mDestination;
    @SerializedName("flight_class")
    private String mFlightClass;
    @SerializedName("flight_duration")
    private String mFlightDuration;
    @SerializedName("marketing_carrier")
    private MarketingCarrier mMarketingCarrier;
    @SerializedName("number")
    private Double mNumber;
    @SerializedName("operating_carrier")
    private OperatingCarrier mOperatingCarrier;
    @SerializedName("origin")
    private Origin mOrigin;
    @SerializedName("technical_stop")
    private Object mTechnicalStop;

    public Aircraft getAircraft() {
        return mAircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        mAircraft = aircraft;
    }

    public List<Amenity> getAmenities() {
        return mAmenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        mAmenities = amenities;
    }

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

    public Double getAvailableStock() {
        return mAvailableStock;
    }

    public void setAvailableStock(Double availableStock) {
        mAvailableStock = availableStock;
    }

    public Boolean getBaggageAllowed() {
        return mBaggageAllowed;
    }

    public void setBaggageAllowed(Boolean baggageAllowed) {
        mBaggageAllowed = baggageAllowed;
    }

    public String getCabinDescription() {
        return mCabinDescription;
    }

    public void setCabinDescription(String cabinDescription) {
        mCabinDescription = cabinDescription;
    }

    public String getCabinType() {
        return mCabinType;
    }

    public void setCabinType(String cabinType) {
        mCabinType = cabinType;
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

    public String getFlightClass() {
        return mFlightClass;
    }

    public void setFlightClass(String flightClass) {
        mFlightClass = flightClass;
    }

    public String getFlightDuration() {
        return mFlightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        mFlightDuration = flightDuration;
    }

    public MarketingCarrier getMarketingCarrier() {
        return mMarketingCarrier;
    }

    public void setMarketingCarrier(MarketingCarrier marketingCarrier) {
        mMarketingCarrier = marketingCarrier;
    }

    public Double getNumber() {
        return mNumber;
    }

    public void setNumber(Double number) {
        mNumber = number;
    }

    public OperatingCarrier getOperatingCarrier() {
        return mOperatingCarrier;
    }

    public void setOperatingCarrier(OperatingCarrier operatingCarrier) {
        mOperatingCarrier = operatingCarrier;
    }

    public Origin getOrigin() {
        return mOrigin;
    }

    public void setOrigin(Origin origin) {
        mOrigin = origin;
    }

    public Object getTechnicalStop() {
        return mTechnicalStop;
    }

    public void setTechnicalStop(Object technicalStop) {
        mTechnicalStop = technicalStop;
    }

}
