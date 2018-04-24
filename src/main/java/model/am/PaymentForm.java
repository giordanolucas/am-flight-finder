package model.am;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PaymentForm {

    @SerializedName("bank")
    private Object mBank;
    @SerializedName("credit_card")
    private CreditCard mCreditCard;

    public Object getBank() {
        return mBank;
    }

    public void setBank(Object bank) {
        mBank = bank;
    }

    public CreditCard getCreditCard() {
        return mCreditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        mCreditCard = creditCard;
    }

}
