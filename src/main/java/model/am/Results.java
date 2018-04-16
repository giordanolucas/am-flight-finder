
package model.am;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Results {

    @SerializedName("clusters")
    private List<Cluster> mClusters;

    public List<Cluster> getClusters() {
        return mClusters;
    }

    public void setClusters(List<Cluster> clusters) {
        mClusters = clusters;
    }

}
