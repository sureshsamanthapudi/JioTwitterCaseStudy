package example.com.jiotwittercasestudy.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import example.com.jiotwittercasestudy.domain.NetworkModule;
import example.com.jiotwittercasestudy.domain.di.DomainApiModule;
import example.com.jiotwittercasestudy.domain.di.DaggerDomainApiModule;
public class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



}
