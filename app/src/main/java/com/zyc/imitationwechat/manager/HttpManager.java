package com.zyc.imitationwechat.manager;

import android.util.Log;

import com.google.gson.Gson;
import com.zyc.imitationwechat.base.BaseHttpResult;
import com.zyc.imitationwechat.http.FileUploadApi;
import com.zyc.imitationwechat.http.HandlerHttpResult;
import com.zyc.imitationwechat.http.HttpApi;
import com.zyc.imitationwechat.http.MyHttpLoggingInterceptor;
import com.zyc.imitationwechat.http.ResultHandler;
import com.zyc.imitationwechat.test.User;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    public static final String TAG = HttpManager.class.getSimpleName();

    private HttpApi mHttpApi;
    private FileUploadApi fileUploadApi;
    private Retrofit retrofit;

    public static final int TIME_OUT = 15;
    public static final String BASE_URL = "http://192.168.10.35:8090/java_war_exploded/";

    private MyHttpLoggingInterceptor mHttpLoggingInterceptor = new MyHttpLoggingInterceptor();
    private Interceptor netInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder newBuilder = request.newBuilder();
            newBuilder.header("Content-Type", "application/json");
            newBuilder.header("Accept", "application/json");
            newBuilder.method(request.method(), request.body());
            return chain.proceed(newBuilder.build());
        }
    };

    private OutputStream mOutputStream;
    private Interceptor netFileInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Connection connection = chain.connection();
            mOutputStream = connection.socket().getOutputStream();
            Request request = chain.request();
            Request.Builder newBuilder = request.newBuilder();
            newBuilder.header("content-type","text/html");
            return chain.proceed(newBuilder.build());
        }
    };

    private static HttpManager instance;
    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }



    private HttpManager () {
        mHttpLoggingInterceptor.setLevel(MyHttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.callTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(mHttpLoggingInterceptor)
                .addInterceptor(netInterceptor);
        OkHttpClient okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mHttpApi = retrofit.create(HttpApi.class);
    }

    public Observable<User> login (String name, String pwd) {
        return mHttpApi.login("login", name, pwd).compose(HandlerHttpResult.handlerResult())
                .compose(HandlerHttpResult.schedulersThread());
    }



    public void uploadFile (String filename) {
        fileUploadApi = retrofit.create(FileUploadApi.class);

        //构建要上传的文件
        File file = new File(filename);
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);

        String descriptionString = "This is a description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);


        Call<ResponseBody> call = fileUploadApi.upload(description, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public <T extends BaseHttpResult> void postFile ( File file, final Class<T> clazz, final ResultHandler<T> resultHandler) {
        // 判断网络连接状况
        if (resultHandler.isNetDisconnected()) {
            resultHandler.onAfterFailure();
            return;
        }
//        FileRequest fileRequest = retrofit.create(FileRequest.class);

        Map<String, RequestBody> paramMap = new HashMap<>();
//        addMultiPart(paramMap, "file", file);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream") , file);
        paramMap.put("file", requestBody);

        // 构建请求
        fileUploadApi = retrofit.create(FileUploadApi.class);
        Call<ResponseBody> call = fileUploadApi.postFile(paramMap);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.i(TAG, "onResponse: " + response.code());
                resultHandler.onBeforeResult();
                try {
                    ResponseBody body = response.body();
                    if (body == null) {
                        resultHandler.onServerError();
                        return;
                    }
                    String string = body.string();
                    T t = new Gson().fromJson(string, clazz);

                    resultHandler.onResult(t);
                } catch (IOException e) {
                    e.printStackTrace();
                    resultHandler.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
                resultHandler.onFailure(t);
                resultHandler.onAfterFailure();
            }
        });
    }

}
