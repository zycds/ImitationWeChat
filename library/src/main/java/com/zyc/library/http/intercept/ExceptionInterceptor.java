package com.zyc.library.http.intercept;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ExceptionInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
