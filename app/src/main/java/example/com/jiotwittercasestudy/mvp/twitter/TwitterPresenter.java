package example.com.jiotwittercasestudy.mvp.twitter;

import java.util.List;

import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.model.User;
import example.com.jiotwittercasestudy.mvp.Presenter;

/**
 * Created by sureshs on 03-01-2018.
 */

public interface TwitterPresenter extends Presenter<TwitterView> {
    void fetchTweets();
    void getUser(String userID);
}
