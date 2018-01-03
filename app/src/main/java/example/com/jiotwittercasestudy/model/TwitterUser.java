package example.com.jiotwittercasestudy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sureshs on 03-01-2018.
 */

public class TwitterUser implements Serializable {

    private static final long serialVersionUID = 3447312558454432747L;

    @SerializedName("screen_name")
    private String screenName;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image_url")
    private String profileImageUrl;

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TwitterUser{");
        sb.append("screenName=").append(screenName);
        sb.append(",name=").append(name);
        sb.append(",profileImageUrl=").append(profileImageUrl);
        sb.append('}');
        return sb.toString();
    }
}
