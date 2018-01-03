package example.com.jiotwittercasestudy.domain;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.com.jiotwittercasestudy.BuildConfig;
import example.com.jiotwittercasestudy.domain.api.TwitterApi;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by sureshs on 03-01-2018.
 */

@Module
public class NetworkModule {

    File cacheFile;

    public NetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    @Provides
    @Singleton
    Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(cache)

                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public TwitterApi providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(TwitterApi.class);
    }
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public ApiService providesService(
            TwitterApi networkService) {
        return new ApiService(networkService);
    }

}
