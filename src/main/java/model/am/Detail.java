package model.am;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Detail {

    @SerializedName("adults")
    private Double mAdults;
    @SerializedName("bonus")
    private Double mBonus;
    @SerializedName("charges")
    private Double mCharges;
    @SerializedName("children")
    private Object mChildren;
    @SerializedName("fare_type")
    private String mFareType;
    @SerializedName("fee")
    private Double mFee;
    @SerializedName("infants")
    private Object mInfants;
    @SerializedName("max_charges")
    private Double mMaxCharges;
    @SerializedName("payment_forms")
    private List<PaymentForm> mPaymentForms;
    @SerializedName("taxes")
    private Double mTaxes;

    public Double getAdults() {
        return mAdults;
    }

    public void setAdults(Double adults) {
        mAdults = adults;
    }

    public Double getBonus() {
        return mBonus;
    }

    public void setBonus(Double bonus) {
        mBonus = bonus;
    }

    public Double getCharges() {
        return mCharges;
    }

    public void setCharges(Double charges) {
        mCharges = charges;
    }

    public Object getChildren() {
        return mChildren;
    }

    public void setChildren(Object children) {
        mChildren = children;
    }

    public String getFareType() {
        return mFareType;
    }

    public void setFareType(String fareType) {
        mFareType = fareType;
    }

    public Double getFee() {
        return mFee;
    }

    public void setFee(Double fee) {
        mFee = fee;
    }

    public Object getInfants() {
        return mInfants;
    }

    public void setInfants(Object infants) {
        mInfants = infants;
    }

    public Double getMaxCharges() {
        return mMaxCharges;
    }

    public void setMaxCharges(Double maxCharges) {
        mMaxCharges = maxCharges;
    }

    public List<PaymentForm> getPaymentForms() {
        return mPaymentForms;
    }

    public void setPaymentForms(List<PaymentForm> paymentForms) {
        mPaymentForms = paymentForms;
    }

    public Double getTaxes() {
        return mTaxes;
    }

    public void setTaxes(Double taxes) {
        mTaxes = taxes;
    }

}
