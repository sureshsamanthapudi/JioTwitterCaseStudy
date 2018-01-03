package example.com.jiotwittercasestudy.ui.hashtag;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.constants.TwitterConstants;
import example.com.jiotwittercasestudy.ui.hashtagtweets.HashTagTweetsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashTagFragment extends Fragment implements HashTagAdapter.SelectedHashTag {

    @BindView(R.id.rvTweetsList)
    RecyclerView rvTweetsList;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    private List<String> hashTagList;

    public static HashTagFragment newInstance(List<String> hashTagList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TwitterConstants.HASHTAG, (Serializable) hashTagList);
        HashTagFragment hashTagFragment = new HashTagFragment();
        hashTagFragment.setArguments(bundle);
        return hashTagFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hash_tag, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hashTagList = (List<String>) getArguments().getSerializable(TwitterConstants.HASHTAG);
        initUI();
    }

    private void initUI() {
        if (hashTagList != null && !hashTagList.isEmpty()) {
            rvTweetsList.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvTweetsList.setLayoutManager(linearLayoutManager);
            List<String> uniqueHashTags = new ArrayList<>();
            Set<String> hashSet = new LinkedHashSet<>();
            hashSet.addAll(hashTagList);
            uniqueHashTags.clear();
            uniqueHashTags.addAll(hashSet);
            HashTagAdapter adapter = new HashTagAdapter(getActivity(), this, uniqueHashTags);
            rvTweetsList.setAdapter(adapter);
        } else {
            rvTweetsList.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void selectedHashTag(String hashTag) {
        Log.i("HashTagFragment", "selectedHashTag:" + hashTag);
        if (!TextUtils.isEmpty(hashTag)) {
            launchHashTagTweetsActivity(hashTag);
        }
    }

    private void launchHashTagTweetsActivity(String hashTag) {
        Intent intent = HashTagTweetsActivity.getCallingIntent(getActivity());
        intent.putExtra(TwitterConstants.SELECTED_TAG, hashTag);
        startActivity(intent);
    }
}
