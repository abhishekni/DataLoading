package com.lazyloding.data.remote.utils;

/**
 * Created by Abhishekn on 28/06/2020.
 * * Network error class.
 */
public class NetworkError {

    public static final int INTERNET_CONNECTION_ERROR = 100;
    public static final int PAGE_NOT_FOUND = 404;
    public static final int SERVER_FAILED = 500;
    public static final int UNKNOWN_ERROR = 50;
    public static final int NETWORK_TIMEOUT = 40;
    public static final int FAILED_TO_CONNECT_WITH_SERVER = 202;
    public static final int ERROR401 = 401;
    public static final int ERROR422 = 422;

    private int code;
    private String message;

    public NetworkError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
