
package model.am;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CreditCard {

    @SerializedName("code")
    private String mCode;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("id")
    private Double mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("type")
    private Object mType;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Double getId() {
        return mId;
    }

    public void setId(Double id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Object getType() {
        return mType;
    }

    public void setType(Object type) {
        mType = type;
    }

}
