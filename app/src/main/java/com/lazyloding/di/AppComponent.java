package com.lazyloding.di;

import android.app.Application;

import com.lazyloding.LazyLoadingApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Abhishekn on 28/06/2020.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(LazyLoadingApp cruAdjusterApp);
}
