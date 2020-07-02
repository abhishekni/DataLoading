package com.lazyloding.di;

import com.lazyloding.ui.fragments.allusers.AllUserFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Abhishekn on 28/06/2020.
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract AllUserFragment allUserFragment();

}
