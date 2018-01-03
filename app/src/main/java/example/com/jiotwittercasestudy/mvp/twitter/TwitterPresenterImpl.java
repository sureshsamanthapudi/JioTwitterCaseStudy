package example.com.jiotwittercasestudy.mvp.twitter;

import android.util.Log;

import java.util.List;

import example.com.jiotwittercasestudy.domain.ApiService;
import example.com.jiotwittercasestudy.domain.RestError;
import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.model.User;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sureshs on 03-01-2018.
 */

public class TwitterPresenterImpl implements TwitterPresenter {

    private ApiService apiService;
    private TwitterView view;
    private CompositeSubscription compositeSubscription;

    public TwitterPresenterImpl(ApiService apiService) {
        this.apiService = apiService;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void attachView(TwitterView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeSubscription.clear();
        this.view = null;

    }

    @Override
    public void fetchTweets() {
        if (view != null) {
            view.showLoading();
            Subscription subscription = apiService.getTweets(new ApiService.GetTweetsCallback() {
                @Override
                public void onSuccess(List<TweetsResponse> tweetsResponses) {
                    if (view != null && tweetsResponses != null && !tweetsResponses.isEmpty()) {
                        view.displayTweets(tweetsResponses);
                    }
                }

                @Override
                public void onError(RestError networkError) {
                }
            });
            compositeSubscription.add(subscription);
        }
    }

    @Override
    public void getUser(String userId) {
        if (view != null) {
            view.showLoading();
            Subscription subscription = apiService.getUser(userId, new ApiService.GetUserCallback() {
                @Override
                public void onSuccess(User user) {
                    if (view != null && user != null) {
                        Log.i("getUser called:", "user:" + user);
                        view.disPlayUserDetails(user);
                        fetchTweets();
                    }
                }

                @Override
                public void onError(RestError networkError) {
                    if (view != null && networkError != null) {
                        view.displayError(networkError.getAppErrorMessage());
                    }
                }
            });
            compositeSubscription.add(subscription);
        }
    }
}
