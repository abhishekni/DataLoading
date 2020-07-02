package com.lazyloding.ui.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Abhishekn on 28/06/2020.
 */

public abstract class BaseActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity implements HasSupportFragmentInjector, CommonViewInteractor{

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;
    public VM viewModel;
    public DB dataBinding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public abstract Class<VM> getViewModel();

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    public void onStart() {
        super.onStart();
        connectCommonViewInteractorWithViewModel();
    }

    private void connectCommonViewInteractorWithViewModel() {
        viewModel.setCommonInteractor(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }

    public DB getDataBinding() {
        return dataBinding;
    }

    @Override
    public void hideKeyboard() {

    }
}
