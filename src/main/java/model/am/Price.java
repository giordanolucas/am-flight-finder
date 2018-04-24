package model.am;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Price {

    @SerializedName("currency")
    private Currency mCurrency;
    @SerializedName("detail")
    private Detail mDetail;
    @SerializedName("total")
    private Double mTotal;

    public Currency getCurrency() {
        return mCurrency;
    }

    public void setCurrency(Currency currency) {
        mCurrency = currency;
    }

    public Detail getDetail() {
        return mDetail;
    }

    public void setDetail(Detail detail) {
        mDetail = detail;
    }

    public Double getTotal() {
        return mTotal;
    }

    public void setTotal(Double total) {
        mTotal = total;
    }

}
