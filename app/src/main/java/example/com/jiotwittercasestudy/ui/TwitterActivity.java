package example.com.jiotwittercasestudy.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jiotwittercasestudy.R;
import example.com.jiotwittercasestudy.ui.twitter.TwitterFragment;

public class TwitterActivity extends BaseActivity implements TwitterInteface {

    @BindView(R.id.mainContainer)
    LinearLayout mainContainer;

    @BindView(R.id.tvToolbarTitle)
    TextView tvToolbarTitle;

    @BindView(R.id.tvToolbarSubTitle)
    TextView tvToolbarSubTitle;

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        launchTwitterFragment();
    }

    @Override
    public void hideActionBar() {
        if (appBarLayout != null) {
            appBarLayout.setVisibility(View.GONE);
        }
    }

    private void launchTwitterFragment() {
        TwitterFragment twitterFragment = TwitterFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, twitterFragment, twitterFragment.getClass().getSimpleName());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void setTitleText(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvToolbarTitle.setText(title);
        }
    }

    @Override
    public void setSubTitleText(String subTitle) {
        if (!TextUtils.isEmpty(subTitle)) {
            tvToolbarSubTitle.setText(subTitle);
        }
    }

    @Override
    public void showActionBar() {
        if (appBarLayout != null) {
            appBarLayout.setVisibility(View.GONE);
        }
    }


}
