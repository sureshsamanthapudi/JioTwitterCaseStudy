package example.com.jiotwittercasestudy.ui.twitter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.constants.TwitterConstants;
import example.com.jiotwittercasestudy.domain.ApiService;
import example.com.jiotwittercasestudy.model.HashTag;
import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.model.User;
import example.com.jiotwittercasestudy.mvp.twitter.TwitterPresenter;
import example.com.jiotwittercasestudy.mvp.twitter.TwitterPresenterImpl;
import example.com.jiotwittercasestudy.mvp.twitter.TwitterView;
import example.com.jiotwittercasestudy.ui.TwitterApplication;
import example.com.jiotwittercasestudy.ui.TwitterInteface;

/**
 * Created by sureshs on 03-01-2018.
 */

public class TwitterFragment extends Fragment implements TwitterView {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Inject
    ApiService apiService;

    private TwitterPagerAdapter pagerAdapter;
    private static final String TAG = "TwitterFramgent";
    private TwitterPresenter presenter;
    private TwitterInteface twitterInteface;

    private ProgressDialog progessDialog;


    public static TwitterFragment newInstance() {
        TwitterFragment twitterFragment = new TwitterFragment();
        return twitterFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TwitterApplication) getActivity().getApplication()).getDomainApiModule().inject(this);
        //( (TwitterActivity)getActivity()).getDomainApiModule().inject(this);
        twitterInteface = (TwitterInteface) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twitter, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }


    private void initUI() {

        presenter = new TwitterPresenterImpl(apiService);
        presenter.attachView(this);
        presenter.getUser(TwitterConstants.OWNER_ID);
    }

    private void showUI(List<TweetsResponse> tweetsResponseList, List<String> hashTagList) {
        Log.i("TAG", "showUI called:");
        pagerAdapter = new TwitterPagerAdapter(tweetsResponseList, hashTagList, this.getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
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
    public void displayTweets(List<TweetsResponse> tweetsResponseList) {
        hideLoading();
        List<TweetsResponse> tweetsResponses = new ArrayList<>();
        List<String> hashTagList = new ArrayList<>();
        if (tweetsResponseList != null && !tweetsResponseList.isEmpty()) {
            tweetsResponses.addAll(tweetsResponseList);
            for (TweetsResponse tweetsResponse : tweetsResponseList) {
                List<HashTag> hashTags = tweetsResponse.getEntities().getHashtags();
                if (hashTags != null && !hashTags.isEmpty()) {
                    for (HashTag hashTag : hashTags) {
                        String text = hashTag.getText();
                        if (!TextUtils.isEmpty(text)) {
                            hashTagList.add(text);
                        }
                    }
                }
            }
            showUI(tweetsResponseList, hashTagList);
        }
    }

    @Override
    public void disPlayUserDetails(User user) {
        if (user != null) {
            twitterInteface.setTitleText(user.getName());
            twitterInteface.setSubTitleText(user.getScreen_name());
        }
    }

    @Override
    public void displayError(String message) {
        hideLoading();
        showUI(null, null);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
