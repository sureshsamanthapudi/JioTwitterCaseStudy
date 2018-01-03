package example.com.jiotwittercasestudy.ui.tweets;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.constants.TwitterConstants;
import example.com.jiotwittercasestudy.model.TweetsResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetsFragment extends Fragment {

    @BindView(R.id.rlRootLayout)
    RelativeLayout rlRootLayout;

    @BindView(R.id.rvTweetsList)
    RecyclerView rvTweetsList;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    private List<TweetsResponse> tweetsResponseList;

    public static TweetsFragment newInstance(List<TweetsResponse> tweetsResponseList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TwitterConstants.TWEETS, (Serializable) tweetsResponseList);
        TweetsFragment tweetsFragment = new TweetsFragment();
        tweetsFragment.setArguments(bundle);
        return tweetsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweets, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tweetsResponseList = (List<TweetsResponse>) getArguments().getSerializable(TwitterConstants.TWEETS);
        initUI();
    }

    private void initUI() {
        if (tweetsResponseList != null && !tweetsResponseList.isEmpty()) {
            rvTweetsList.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvTweetsList.setLayoutManager(linearLayoutManager);
            TweetsAdapter adapter = new TweetsAdapter(getActivity(), tweetsResponseList);
            rvTweetsList.setAdapter(adapter);
        } else {
            rvTweetsList.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }
    }
}
