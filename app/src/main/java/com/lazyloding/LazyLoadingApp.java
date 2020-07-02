package com.lazyloding;

import android.app.Activity;
import android.app.Application;

import androidx.fragment.app.Fragment;

import com.lazyloding.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Abhishekn on 28/06/2020
 */

public class LazyLoadingApp extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    private static LazyLoadingApp cruAdjusterApp;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        cruAdjusterApp = this;
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }

    public static LazyLoadingApp getInstance() {
        return cruAdjusterApp;
    }
}
