package example.com.jiotwittercasestudy.mvp.hashtagtweets;

import java.util.List;

import example.com.jiotwittercasestudy.model.HashSearch;
import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.model.User;
import example.com.jiotwittercasestudy.mvp.View;

/**
 * Created by sureshs on 03-01-2018.
 */

public interface HashTagTweetsView extends View{
    void showLoading();
    void hideLoading();
    void displayTweets(HashSearch hashSearch);
    void displayError(String str);
}
