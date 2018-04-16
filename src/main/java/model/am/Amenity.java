
package model.am;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Amenity {

    @SerializedName("description")
    private Object mDescription;
    @SerializedName("included")
    private Boolean mIncluded;
    @SerializedName("type")
    private String mType;

    public Object getDescription() {
        return mDescription;
    }

    public void setDescription(Object description) {
        mDescription = description;
    }

    public Boolean getIncluded() {
        return mIncluded;
    }

    public void setIncluded(Boolean included) {
        mIncluded = included;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
