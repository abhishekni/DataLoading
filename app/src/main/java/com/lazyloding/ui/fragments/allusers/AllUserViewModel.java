package com.lazyloding.ui.fragments.allusers;

import android.annotation.SuppressLint;

import com.lazyloding.data.Repository;
import com.lazyloding.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AllUserViewModel extends BaseViewModel {

    private final Repository repository;
    private CompositeDisposable disposable;

    @Inject
    public AllUserViewModel(Repository repository) {
        this.repository = repository;
        disposable  = new CompositeDisposable();
    }

    /**
     * integrating feeds api
     */
    @SuppressLint("CheckResult")
    public void getLazyLodingApi() {
        disposable.add(repository.lazyLodingApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lazyLoding -> lazyLodingMutableLiveData.postValue(lazyLoding), Throwable::printStackTrace)
        );
    }

}
