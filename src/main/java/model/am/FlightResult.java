
package model.am;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class FlightResult {

    @SerializedName("results")
    private Results mResults;

    public Results getResults() {
        return mResults;
    }

    public void setResults(Results results) {
        mResults = results;
    }

}
