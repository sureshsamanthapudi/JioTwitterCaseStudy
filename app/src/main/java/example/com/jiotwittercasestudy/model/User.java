package example.com.jiotwittercasestudy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sureshs on 03-01-2018.
 */

public class User implements Serializable {

    private static final long serialVersionUID = 7250770018764834791L;

    @SerializedName("screen_name")
    private String screen_name;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image_url")
    private String profileImageUrl;

    @SerializedName("profile_image_url_https")
    private String profileImageUrlHttps;

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    public void setProfileImageUrlHttps(String profileImageUrlHttps) {
        this.profileImageUrlHttps = profileImageUrlHttps;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name=").append(name);
        sb.append(",screen_name=").append(screen_name);
        sb.append(",profileImageUrl=").append(profileImageUrl);
        sb.append(",profileImageUrlHttps=").append(profileImageUrlHttps);
        sb.append('}');
        return sb.toString();
    }
}
