package example.com.jiotwittercasestudy.ui;

import android.app.Application;

import java.io.File;

import example.com.jiotwittercasestudy.domain.NetworkModule;
import example.com.jiotwittercasestudy.domain.di.DaggerDomainApiModule;
import example.com.jiotwittercasestudy.domain.di.DomainApiModule;

/**
 * Created by sureshs on 03-01-2018.
 */

public class TwitterApplication extends Application {

    DomainApiModule domainApiModule;

    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        domainApiModule = DaggerDomainApiModule.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public DomainApiModule getDomainApiModule() {
        return domainApiModule;
    }
}
