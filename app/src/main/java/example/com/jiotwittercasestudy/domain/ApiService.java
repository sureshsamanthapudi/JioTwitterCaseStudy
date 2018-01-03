package example.com.jiotwittercasestudy.domain;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import example.com.jiotwittercasestudy.constants.TwitterConstants;
import example.com.jiotwittercasestudy.domain.api.TwitterApi;
import example.com.jiotwittercasestudy.model.HashSearch;
import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.model.User;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by sureshs on 03-01-2018.
 */

public class ApiService {

    private final TwitterApi twitterApi;

    public ApiService(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    public Subscription getUser(String userId, final  GetUserCallback callback){
        TwitterConstants constants = new TwitterConstants();
        return twitterApi.getUser(constants.getAuthHeader("https://api.twitter.com/1.1/users/show.json?user_id=" + userId),userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends User>>() {
                    @Override
                    public Observable<? extends User> call(Throwable throwable) {
                        Log.i("getUser called","error:"+throwable);
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("getUser called","error:"+e);
                        callback.onError(new RestError(e));

                    }

                    @Override
                    public void onNext(User user) {
                        callback.onSuccess(user);

                    }
                });
    }
    public Subscription getTweets(final GetTweetsCallback callback) {
        TwitterConstants constants = new TwitterConstants();
        String count = "50";
        return twitterApi.getTweets(constants.getAuthHeader("https://api.twitter.com/1.1/statuses/home_timeline.json?count=" + count),count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<TweetsResponse>>>() {
                    @Override
                    public Observable<? extends List<TweetsResponse>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<TweetsResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new RestError(e));

                    }

                    @Override
                    public void onNext(List<TweetsResponse> cityListResponse) {
                        callback.onSuccess(cityListResponse);
                    }
                });
    }


    public Subscription getTweets(final String hashTag, final GetHashTweetsCallback callback) {
        TwitterConstants constants = new TwitterConstants();
        String query = constants.getQuery(hashTag);
        String paramQuery = query.substring(2,query.length());
        try {
            query = URLEncoder.encode(paramQuery,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return twitterApi.getHashTweets(constants.getAuthHeader("https://api.twitter.com/1.1/search/tweets.json?q="+query),paramQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends HashSearch>>() {
                    @Override
                    public Observable<? extends HashSearch> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<HashSearch>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new RestError(e));

                    }

                    @Override
                    public void onNext(HashSearch hashSearch) {
                        Log.i("onNext","hashsearch:"+hashSearch);
                        callback.onSuccess(hashSearch);
                    }
                });
    }

    public  interface GetUserCallback{
        void onSuccess(User user);

        void onError(RestError restError);
    }
    public interface GetTweetsCallback{
        void onSuccess(List<TweetsResponse> tweetsResponseList);

        void onError(RestError restError);
    }

    public interface GetHashTweetsCallback{
        void onSuccess(HashSearch hashSearch);

        void onError(RestError restError);
    }
}
