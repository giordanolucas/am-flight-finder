package model.am;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Baggage {

    @SerializedName("dimension")
    private Object mDimension;
    @SerializedName("dimension_unit")
    private Object mDimensionUnit;
    @SerializedName("quantity")
    private Double mQuantity;
    @SerializedName("weight")
    private Object mWeight;
    @SerializedName("weight_unit")
    private Object mWeightUnit;

    public Object getDimension() {
        return mDimension;
    }

    public void setDimension(Object dimension) {
        mDimension = dimension;
    }

    public Object getDimensionUnit() {
        return mDimensionUnit;
    }

    public void setDimensionUnit(Object dimensionUnit) {
        mDimensionUnit = dimensionUnit;
    }

    public Double getQuantity() {
        return mQuantity;
    }

    public void setQuantity(Double quantity) {
        mQuantity = quantity;
    }

    public Object getWeight() {
        return mWeight;
    }

    public void setWeight(Object weight) {
        mWeight = weight;
    }

    public Object getWeightUnit() {
        return mWeightUnit;
    }

    public void setWeightUnit(Object weightUnit) {
        mWeightUnit = weightUnit;
    }

}
