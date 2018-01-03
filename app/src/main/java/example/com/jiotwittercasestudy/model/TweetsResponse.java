package example.com.jiotwittercasestudy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sureshs on 03-01-2018.
 */

public class TweetsResponse implements Serializable {

    private static final long serialVersionUID = 1337041264970792017L;

    @SerializedName("text")
    private String text;

    @SerializedName("user")
    private User user;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("entities")
    private Entities entities;

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TweetsResponse{");
        sb.append("user=").append(user);
        sb.append(",text=").append(text);
        sb.append(",created_at=").append(created_at);
        sb.append(",entities=").append(entities);
        sb.append('}');
        return sb.toString();
    }
}
