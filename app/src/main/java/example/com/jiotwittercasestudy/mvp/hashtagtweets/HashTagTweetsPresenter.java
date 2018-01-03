package example.com.jiotwittercasestudy.mvp.hashtagtweets;

import example.com.jiotwittercasestudy.mvp.Presenter;

/**
 * Created by sureshs on 03-01-2018.
 */

public interface HashTagTweetsPresenter extends Presenter<HashTagTweetsView> {
    void fetchTweets(String hashTag);

}
