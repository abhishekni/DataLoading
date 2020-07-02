package com.lazyloding.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.lazyloding.ui.fragments.allusers.AllUserViewModel;
import com.lazyloding.ui.home.HomeViewModel;
import com.lazyloding.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Abhishekn on 28/06/2020.
 */

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract  ViewModel bindsHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AllUserViewModel.class)
    abstract  ViewModel bindsAllUserViewModel(AllUserViewModel allUserViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
