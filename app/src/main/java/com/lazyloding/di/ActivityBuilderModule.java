package com.lazyloding.di;


import com.lazyloding.ui.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Abhishekn on 28/06/2020.
 */
@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector (modules = FragmentBuilderModule.class)
    abstract HomeActivity homeActivity();

}
