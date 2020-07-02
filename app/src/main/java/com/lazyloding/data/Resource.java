/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lazyloding.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.lazyloding.data.Status.ERROR;
import static com.lazyloding.data.Status.LOADING;
import static com.lazyloding.data.Status.SUCCESS;


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
public class Resource<T> {

    @SerializedName(value = "status", alternate = {"reason"})
    @Expose
    @NonNull
    public final com.lazyloding.data.Status status;
    @SerializedName(value = "data", alternate = {"device", "items"})
    @Expose
    @Nullable
    public final T data;
    @SerializedName("message")
    @Expose
    @Nullable public final String message;


    private Resource(@NonNull com.lazyloding.data.Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }
}
