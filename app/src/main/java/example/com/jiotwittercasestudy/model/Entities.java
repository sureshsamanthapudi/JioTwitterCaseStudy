package example.com.jiotwittercasestudy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sureshs on 03-01-2018.
 */

public class Entities implements Serializable {


    private static final long serialVersionUID = 6223754658775179978L;

    @SerializedName("hashtags")
    private List<HashTag> hashtags;

    public List<HashTag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<HashTag> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entities{");
        sb.append("hashtags=").append(hashtags);
        sb.append('}');
        return sb.toString();
    }
}
