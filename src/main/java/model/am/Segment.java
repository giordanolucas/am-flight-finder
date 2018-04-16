
package model.am;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Segment {

    @SerializedName("choices")
    private List<Choice> mChoices;

    public List<Choice> getChoices() {
        return mChoices;
    }

    public void setChoices(List<Choice> choices) {
        mChoices = choices;
    }

}
