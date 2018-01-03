package example.com.jiotwittercasestudy.ui.hashtagtweets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.constants.TwitterConstants;
import example.com.jiotwittercasestudy.ui.BaseActivity;

public class HashTagTweetsActivity extends BaseActivity implements HashTagInterface {

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.mainContainer)
    LinearLayout mainContainer;

    @BindView(R.id.tvToolbarTitle)
    TextView tvToolbarTitle;


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HashTagTweetsActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash_tag_tweets);
        ButterKnife.bind(this);
        launchHashTweetsFragment();
    }

    private void launchHashTweetsFragment() {
        String SELECTED_HASH = getIntent().getStringExtra(TwitterConstants.SELECTED_TAG);
        setTitleText(SELECTED_HASH);
        HashTagTweetsFragment twitterFragment = HashTagTweetsFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(TwitterConstants.SELECTED_TAG, SELECTED_HASH);
        twitterFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, twitterFragment, twitterFragment.getClass().getSimpleName());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);

        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void setTitleText(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvToolbarTitle.setText(getResources().getString(R.string.label_tag_name, title));
        }
    }

    @Override
    public void showActionBar() {
        appBarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideActionBar() {
        appBarLayout.setVisibility(View.GONE);
    }
}
