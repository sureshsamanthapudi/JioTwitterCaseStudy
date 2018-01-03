package example.com.jiotwittercasestudy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sureshs on 03-01-2018.
 */

public class HashSearch implements Serializable {

    private static final long serialVersionUID = -87848500068236898L;

    @SerializedName("statuses")
    private List<HashSearchTweet> status;


    public List<HashSearchTweet> getStatus() {
        return status;
    }

    public void setStatus(List<HashSearchTweet> status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HashSearch{");
        sb.append("status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
