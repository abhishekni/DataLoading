package com.lazyloding.data;

import com.lazyloding.data.remote.APIDbService;
import com.lazyloding.data.remote.model.LazyLoading;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Abhishekn on 28/06/2020.
 */
@Singleton
public class Repository {

    private final APIDbService mAPIDbService;

    @Inject
    public Repository(APIDbService mAPIDbService) {
        this.mAPIDbService = mAPIDbService;
    }

    public Single<LazyLoading> lazyLodingApi() {
        return mAPIDbService.lazyLodingApi();
    }


}
