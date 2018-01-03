package example.com.jiotwittercasestudy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sureshs on 03-01-2018.
 */

public class Tweet implements Serializable {

    private static final long serialVersionUID = -8553937940588916377L;

    @SerializedName("id")
    private String id;

    @SerializedName("text")
    private String text;

    @SerializedName("in_reply_to_status_id")
    private String inReplyToStatusId;

    @SerializedName("in_reply_to_user_id")
    private String inReplyToUserId;

    @SerializedName("in_reply_to_screen_name")
    private String inReplyToScreenName;

    @SerializedName("user")
    private TwitterUser user;

    @SerializedName("created_at")
    private String dateCreated;

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    public void setInReplyToStatusId(String inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    public String getInReplyToUserId() {
        return inReplyToUserId;
    }

    public void setInReplyToUserId(String inReplyToUserId) {
        this.inReplyToUserId = inReplyToUserId;
    }

    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    public void setInReplyToScreenName(String inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    public TwitterUser getUser() {
        return user;
    }

    public void setUser(TwitterUser user) {
        this.user = user;
    }

    @Override
    public String  toString(){
        final StringBuilder sb = new StringBuilder("Tweet{");
        sb.append("dateCreated=").append(dateCreated);
        sb.append(",id=").append(id);
        sb.append(",user=").append(user);
        sb.append(",text=").append(text);
        sb.append(",inReplyToScreenName=").append(inReplyToScreenName);
        sb.append(",inReplyToStatusId=").append(inReplyToStatusId);
        sb.append(",inReplyToUserId=").append(inReplyToUserId);
        sb.append('}');
        return sb.toString();
    }
}
