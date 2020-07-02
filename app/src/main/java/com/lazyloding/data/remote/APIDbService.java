package com.lazyloding.data.remote;

import com.lazyloding.data.remote.model.LazyLoading;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Abhishekn on 28/06/2020.
 */

public interface APIDbService {

    @GET("facts")
    Single<LazyLoading> lazyLodingApi();


}
