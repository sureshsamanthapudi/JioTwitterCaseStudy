package example.com.jiotwittercasestudy.ui.hashtagtweets;

import android.support.annotation.Keep;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.ui.CircleImageView;

/**
 * Created by sureshs on 03-01-2018.
 */

public class HashTagTweetsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.profile_image)
    CircleImageView profile_image;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvTimeStamp)
    TextView tvTimeStamp;

    @BindView(R.id.tvTweetText)
    TextView tvTweetText;

    @Keep
    public HashTagTweetsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
