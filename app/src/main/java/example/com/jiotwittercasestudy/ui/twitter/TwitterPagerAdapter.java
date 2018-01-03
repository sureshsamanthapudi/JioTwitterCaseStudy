package example.com.jiotwittercasestudy.ui.twitter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.ui.hashtag.HashTagFragment;
import example.com.jiotwittercasestudy.ui.tweets.TweetsFragment;

/**
 * Created by sureshs on 03-01-2018.
 */

public class TwitterPagerAdapter extends FragmentStatePagerAdapter {

    private List<TweetsResponse> tweetsResponseList;
    private List<String> hashTagList;

    public TwitterPagerAdapter(List<TweetsResponse> tweetsResponseList, List<String> hashTagList, FragmentManager fm) {
        super(fm);
        this.tweetsResponseList = tweetsResponseList;
        this.hashTagList = hashTagList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = TweetsFragment.newInstance(tweetsResponseList);
        } else if (position == 1) {
            fragment = HashTagFragment.newInstance(hashTagList);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Tweets";
        } else if (position == 1) {
            title = "Top 10";
        }
        return title;
    }
}
