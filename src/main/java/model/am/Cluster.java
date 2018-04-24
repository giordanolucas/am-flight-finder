package model.am;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Cluster {

    @SerializedName("difference")
    private Double mDifference;
    @SerializedName("flight_scope")
    private String mFlightScope;
    @SerializedName("flight_type")
    private String mFlightType;
    @SerializedName("price")
    private Price mPrice;
    @SerializedName("provider")
    private String mProvider;
    @SerializedName("segments")
    private List<Segment> mSegments;
    @SerializedName("total")
    private Double mTotal;
    @SerializedName("validating_carrier")
    private String mValidatingCarrier;

    public Double getDifference() {
        return mDifference;
    }

    public void setDifference(Double difference) {
        mDifference = difference;
    }

    public String getFlightScope() {
        return mFlightScope;
    }

    public void setFlightScope(String flightScope) {
        mFlightScope = flightScope;
    }

    public String getFlightType() {
        return mFlightType;
    }

    public void setFlightType(String flightType) {
        mFlightType = flightType;
    }

    public Price getPrice() {
        return mPrice;
    }

    public void setPrice(Price price) {
        mPrice = price;
    }

    public String getProvider() {
        return mProvider;
    }

    public void setProvider(String provider) {
        mProvider = provider;
    }

    public List<Segment> getSegments() {
        return mSegments;
    }

    public void setSegments(List<Segment> segments) {
        mSegments = segments;
    }

    public Double getTotal() {
        return mTotal;
    }

    public void setTotal(Double total) {
        mTotal = total;
    }

    public String getValidatingCarrier() {
        return mValidatingCarrier;
    }

    public void setValidatingCarrier(String validatingCarrier) {
        mValidatingCarrier = validatingCarrier;
    }

}
