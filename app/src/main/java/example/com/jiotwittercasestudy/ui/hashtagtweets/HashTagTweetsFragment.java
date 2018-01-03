package example.com.jiotwittercasestudy.ui.hashtagtweets;


import android.app.ProgressDialog;
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
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.constants.TwitterConstants;
import example.com.jiotwittercasestudy.domain.ApiService;
import example.com.jiotwittercasestudy.model.HashSearch;
import example.com.jiotwittercasestudy.model.HashSearchTweet;
import example.com.jiotwittercasestudy.mvp.hashtagtweets.HashTagTweetsPresenter;
import example.com.jiotwittercasestudy.mvp.hashtagtweets.HashTagTweetsPresenterImpl;
import example.com.jiotwittercasestudy.mvp.hashtagtweets.HashTagTweetsView;
import example.com.jiotwittercasestudy.ui.TwitterApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashTagTweetsFragment extends Fragment implements HashTagTweetsView {

    @BindView(R.id.rlRootLayout)
    RelativeLayout rlRootLayout;

    @BindView(R.id.rvTweetsList)
    RecyclerView rvTweetsList;

    @BindView(R.id.tvNoData)
    TextView tvNoData;

    @Inject
    ApiService apiService;

    private HashTagTweetsPresenter presenter;
    private ProgressDialog progessDialog;

    public static HashTagTweetsFragment newInstance() {
        HashTagTweetsFragment hashTagTweetsFragment = new HashTagTweetsFragment();
        return hashTagTweetsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TwitterApplication) getActivity().getApplication()).getDomainApiModule().inject(this);
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
        initUI();
    }

    private void initUI() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String hashTag = bundle.getString(TwitterConstants.SELECTED_TAG);
            presenter = new HashTagTweetsPresenterImpl(apiService);
            presenter.attachView(this);
            presenter.fetchTweets(hashTag);
        }
    }

    @Override
    public void showLoading() {
        if (progessDialog == null) {
            progessDialog = new ProgressDialog(getActivity());
        }
        progessDialog.setMessage("Processing request.");
        progessDialog.setCancelable(false);
        progessDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progessDialog != null) {
            progessDialog.dismiss();
        }
    }

    @Override
    public void displayTweets(HashSearch hashSearch) {
        hideLoading();
        List<HashSearchTweet> hashSearchTweets = hashSearch.getStatus();
        if (hashSearchTweets != null && !hashSearchTweets.isEmpty()) {

            rvTweetsList.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvTweetsList.setLayoutManager(linearLayoutManager);
            HashTagTweetsAdapter adapter = new HashTagTweetsAdapter(getActivity(), hashSearchTweets);
            rvTweetsList.setAdapter(adapter);
        } else {
            rvTweetsList.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayError(String message) {
        hideLoading();
        rvTweetsList.setVisibility(View.GONE);
        tvNoData.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
