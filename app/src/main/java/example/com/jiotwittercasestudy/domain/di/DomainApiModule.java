package example.com.jiotwittercasestudy.domain.di;

import javax.inject.Singleton;

import dagger.Component;
import example.com.jiotwittercasestudy.domain.NetworkModule;
import example.com.jiotwittercasestudy.ui.hashtagtweets.HashTagTweetsFragment;
import example.com.jiotwittercasestudy.ui.tweets.TweetsFragment;
import example.com.jiotwittercasestudy.ui.twitter.TwitterFragment;

/**
 * Created by sureshs on 03-01-2018.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface DomainApiModule {
    void inject(TwitterFragment twitterFragment);
    void inject(HashTagTweetsFragment tweetsFragment);
}
