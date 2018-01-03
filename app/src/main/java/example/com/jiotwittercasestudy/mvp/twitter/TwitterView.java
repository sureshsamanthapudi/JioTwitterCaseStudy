package example.com.jiotwittercasestudy.mvp.twitter;

import java.util.List;

import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.model.User;
import example.com.jiotwittercasestudy.mvp.View;

/**
 * Created by sureshs on 03-01-2018.
 */

public interface TwitterView extends View{
    void showLoading();
    void hideLoading();
    void displayTweets(List<TweetsResponse> tweetsResponseList);
    void disPlayUserDetails(User user);
    void displayError(String message);
}
