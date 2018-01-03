package example.com.jiotwittercasestudy.ui.tweets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.model.User;

/**
 * Created by sureshs on 03-01-2018.
 */

public class TweetsAdapter extends RecyclerView.Adapter<TweetsViewHolder> {

    private List<TweetsResponse> tweetsResponseList;
    private Context context;

    public TweetsAdapter(Context context, List<TweetsResponse> tweetsResponseList) {
        this.tweetsResponseList = tweetsResponseList;
        this.context = context;
    }

    @Override
    public TweetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweets_list, null);
        TweetsViewHolder viewHolder = new TweetsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TweetsViewHolder holder, int position) {
        TweetsResponse tweetsResponse = tweetsResponseList.get(position);
        if (tweetsResponse != null) {
            holder.tvTweetText.setText(tweetsResponse.getText());
            holder.tvTimeStamp.setText(tweetsResponse.getCreated_at());
            User user = tweetsResponse.getUser();
            if (user != null) {
                holder.tvName.setText(tweetsResponse.getUser().getName());
                Glide.with(context)
                        .load(tweetsResponse.getUser().getProfileImageUrl())
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
