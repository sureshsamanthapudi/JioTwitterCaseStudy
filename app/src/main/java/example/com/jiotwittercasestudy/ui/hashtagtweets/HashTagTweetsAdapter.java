package example.com.jiotwittercasestudy.ui.hashtagtweets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.model.HashSearchTweet;
import example.com.jiotwittercasestudy.model.User;

/**
 * Created by sureshs on 03-01-2018.
 */

public class HashTagTweetsAdapter extends RecyclerView.Adapter<HashTagTweetsViewHolder> {


    private List<HashSearchTweet> tweetsResponseList;
    private Context context;

    public HashTagTweetsAdapter(Context context, List<HashSearchTweet> tweetsResponseList) {
        this.tweetsResponseList = tweetsResponseList;
        this.context = context;
    }

    @Override
    public HashTagTweetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweets_list, null);
        HashTagTweetsViewHolder viewHolder = new HashTagTweetsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HashTagTweetsViewHolder holder, int position) {
        HashSearchTweet tweetsResponse = tweetsResponseList.get(position);
        if (tweetsResponse != null) {
            holder.tvTweetText.setText(tweetsResponse.getText());
            holder.tvTimeStamp.setText(tweetsResponse.getCreatedAt());
            User user = tweetsResponse.getUser();
            if (user != null) {
                holder.tvName.setText(user.getName());
                Glide.with(context)
                        .load(user.getProfileImageUrl())
                        .override(300, 200)
                        .into(holder.profile_image);
            }

        }
    }

    @Override
    public int getItemCount() {
        return tweetsResponseList.size();
    }
}
