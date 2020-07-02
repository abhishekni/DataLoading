package com.lazyloding.di;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.lazyloding.data.remote.APIDbService;
import com.lazyloding.data.remote.ApiConstants;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhishekn on 28/06/2020.
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        //okHttpClient.addInterceptor(new RequestInterceptor());
        //okHttpClient.addInterceptor(new ResponseErrorInterceptor());
        okHttpClient .addInterceptor(interceptor);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    APIDbService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .serializeNulls()
                        .setPrettyPrinting()
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(APIDbService.class);
    }


    /*inFromRightAnimation*/
    @Provides
    @Singleton
    @Named("inFromRightAnimation")
    public Animation inFromRightAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    /*outToLeftAnimation*/
    @Provides
    @Singleton
    @Named("outToLeftAnimation")
    public Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    /*inFromLeftAnimation*/
    @Provides
    @Singleton
    @Named("inFromLeftAnimation")
    public Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    /* outToRightAnimation*/
    @Provides
    @Singleton
    @Named("outToRightAnimation")
    public Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

}
