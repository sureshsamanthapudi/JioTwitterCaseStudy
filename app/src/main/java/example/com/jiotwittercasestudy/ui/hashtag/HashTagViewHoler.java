package example.com.jiotwittercasestudy.ui.hashtag;

import android.support.annotation.Keep;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jiotwittercasestudy.R;

/**
 * Created by sureshs on 03-01-2018.
 */

public class HashTagViewHoler extends RecyclerView.ViewHolder {

    @BindView(R.id.tvHashTag)
    TextView tvHashTag;

    @Keep
    public HashTagViewHoler(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
