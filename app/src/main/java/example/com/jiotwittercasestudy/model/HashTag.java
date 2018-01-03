package example.com.jiotwittercasestudy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sureshs on 03-01-2018.
 */

public class HashTag implements Serializable {

    private static final long serialVersionUID = -7504107824695549403L;

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HashTag{");
        sb.append("text=").append(text);
        sb.append('}');
        return sb.toString();
    }
}
