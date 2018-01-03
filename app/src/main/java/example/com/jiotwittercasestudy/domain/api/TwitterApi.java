package example.com.jiotwittercasestudy.domain.api;

import java.util.List;

import example.com.jiotwittercasestudy.constants.TwitterConstants;
import example.com.jiotwittercasestudy.model.HashSearch;
import example.com.jiotwittercasestudy.model.TweetsResponse;
import example.com.jiotwittercasestudy.model.User;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sureshs on 03-01-2018.
 */

public interface TwitterApi {

    @GET("users/show.json")
    Observable<User> getUser(@Header(TwitterConstants.AUTHORIZATION)String oAuthAuthorization, @Query("user_id") String userId);

    @GET("statuses/home_timeline.json")
    Observable<List<TweetsResponse>> getTweets(@Header(TwitterConstants.AUTHORIZATION)String oAuthAuthorization, @Query("count") String count);

    @GET("search/tweets.json")
    Observable<HashSearch> getHashTweets(@Header(TwitterConstants.AUTHORIZATION)String oAuthAuthorization, @Query("q") String searchQuery);

}
