package com.lazyloding.ui.base;


import android.content.Context;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lazyloding.data.remote.model.LazyLoading;

import org.json.JSONObject;

import java.nio.charset.Charset;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable;
    public ObservableBoolean dataLoading = new ObservableBoolean();
    protected CommonViewInteractor commonViewInteractor;
    protected Context context;

    public MutableLiveData<LazyLoading> getLazyLodingMutableLiveData() {
        return lazyLodingMutableLiveData;
    }

    protected MutableLiveData<LazyLoading> lazyLodingMutableLiveData = new MutableLiveData<>();

    public BaseViewModel() {
        disposable = new CompositeDisposable();
    }

    public void setCommonInteractor(CommonViewInteractor commonInteractor, Context context ) {
        this.commonViewInteractor = commonInteractor;
        this.context = context;
    }


    public MutableLiveData<String> getValidateSupervisorResetPassword() {
        return validateSupervisorResetPassword;
    }

    public final MutableLiveData<String> validateSupervisorResetPassword = new MutableLiveData<>();

    /**
     * Handling Status/code/reason.
     * @param response
     * @return
     */
    public String makeErrorMessage(String response) {
        try {
            String responseErrorJsonBodyStr = response;/*getErrorJsonFromResponseBody(response);*/
            if (!responseErrorJsonBodyStr.isEmpty()) {
                JSONObject jsonObject = new JSONObject(responseErrorJsonBodyStr);
                if (jsonObject.has("message") && jsonObject.has("status")) {
                    return /*jsonObject.optString("status") + ":" +*/ jsonObject.optString("message");
                } else if (jsonObject.has("reason")) {
                    return jsonObject.optString("reason");
                } else {
                    return jsonObject.optString("status") + ":" + jsonObject.optString("code") + " " + jsonObject.optString("reason");
                }
            } else {
                return response.toString();
            }
        } catch (Exception e) {
            return response.toString();
        }
    }

    private String getErrorJsonFromResponseBody(Response response) throws Exception {
        // response.body().string() calling it twice gives illegal state exception so it is better to make it clone
        // The string method on the response will read the input (network) stream and convert it into a string. So it dynamically builds the string and returns it to you. The second time you call it, the network stream has already been consumed and is no longer available.
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // request the entire body.
        Buffer buffer = source.buffer();
        // clone buffer before reading from it
        return buffer.clone().readString(Charset.forName("UTF-8"));
    }

    /**
     * Clearing disposable object when viewmodel clear
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
