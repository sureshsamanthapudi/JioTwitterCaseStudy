package example.com.jiotwittercasestudy.mvp.hashtagtweets;

import android.util.Log;

import example.com.jiotwittercasestudy.domain.ApiService;
import example.com.jiotwittercasestudy.domain.RestError;
import example.com.jiotwittercasestudy.model.HashSearch;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by sureshs on 03-01-2018.
 */

public class HashTagTweetsPresenterImpl implements HashTagTweetsPresenter {

    private ApiService apiService;
    private HashTagTweetsView view;
    private CompositeSubscription compositeSubscription;

    public HashTagTweetsPresenterImpl(ApiService apiService) {
        this.apiService = apiService;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void attachView(HashTagTweetsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeSubscription.clear();
        this.view = null;

    }

    @Override
    public void fetchTweets(String tagName) {
        if (view != null) {
            view.showLoading();

            Subscription subscription = apiService.getTweets(tagName, new ApiService.GetHashTweetsCallback() {

                @Override
                public void onSuccess(HashSearch hashSearch) {
                    if (view != null && hashSearch != null) {
                        view.displayTweets(hashSearch);
                    }
                    Log.i("HashTagTweetsPresenter", "fetchTweets" + hashSearch);
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
