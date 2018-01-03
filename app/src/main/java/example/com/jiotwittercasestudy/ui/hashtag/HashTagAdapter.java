package example.com.jiotwittercasestudy.ui.hashtag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import example.com.jiotwittercasestudy.R;

/**
 * Created by sureshs on 03-01-2018.
 */

public class HashTagAdapter extends RecyclerView.Adapter<HashTagViewHoler> {

    private Context context;
    private List<String> hashTagList;
    private SelectedHashTag selectedHashTag;

    public HashTagAdapter(Context context, SelectedHashTag selectedHashTag, List<String> hashTagList) {
        this.context = context;
        this.selectedHashTag = selectedHashTag;
        this.hashTagList = hashTagList;
    }

    @Override
    public HashTagViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hashtaglist, null);
        HashTagViewHoler hashTagViewHoler = new HashTagViewHoler(view);
        return hashTagViewHoler;
    }

    @Override
    public void onBindViewHolder(HashTagViewHoler holder, int position) {
        String hashTag = hashTagList.get(position);
        holder.tvHashTag.setText(context.getResources().getString(R.string.label_tag_name, hashTag));
        holder.tvHashTag.setTag(position);
        holder.tvHashTag.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return hashTagList.size();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            selectedHashTag.selectedHashTag(hashTagList.get(position));
        }
    };

    public interface SelectedHashTag {
        public void selectedHashTag(String text);
    }
}
