package com.lazyloding.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class ApiResponseTest {
    @Test
    public void exception() {
        Exception exception = new Exception("foo");
        ApiResponse<String> apiResponse = new ApiResponse<>(exception);
        assertThat(apiResponse.getMessageOrSuccess(), notNullValue());
        assertThat(apiResponse.getData(), nullValue());
        assertThat(apiResponse.getCode(), is(500));
        assertThat(apiResponse.getMessage(), is("foo"));
    }

    @Test
    public void success() {
        ApiResponse<String> apiResponse = new ApiResponse<>(Response.success("foo"));
        assertThat(apiResponse.getMessageOrSuccess(), nullValue());
        assertThat(apiResponse.getCode(), is(200));
        assertThat(apiResponse.getMessage(), is("foo"));
    }

    @Test
    public void error() {
        ApiResponse<String> response = new ApiResponse<String>(Response.error(400,
                ResponseBody.create(MediaType.parse("application/txt"), "blah")));
        assertThat(response.getCode(), is(400));
        assertThat(response.getMessage(), is("blah"));
    }
}