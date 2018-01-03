package example.com.jiotwittercasestudy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sureshs on 03-01-2018.
 */

public class HashSearchTweet implements Serializable {

    private static final long serialVersionUID = -5475515686437055249L;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("text")
    private String text;

    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HashSearchTweet{");
        sb.append("createdAt=").append(createdAt);
        sb.append("text=").append(text);
        sb.append("user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}

