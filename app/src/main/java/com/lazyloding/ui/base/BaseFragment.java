package com.lazyloding.ui.base;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Abhishekn on 28/06/2020.
 * *Generic BaseFragment with view model and databinding class.
 */

public abstract class BaseFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends ViewLifecycleFragment implements CommonViewInteractor{


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
        viewModel.setCommonInteractor(this, getContext());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        dataBinding.setLifecycleOwner(getViewLifecycleOwner());
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel());

    }

    public DB getDataBinding() {
        return dataBinding;
    }

    @Override
    public void hideKeyboard() {

    }
}
