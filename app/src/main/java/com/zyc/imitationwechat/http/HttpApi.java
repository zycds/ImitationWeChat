package com.zyc.imitationwechat.http;

import com.zyc.imitationwechat.base.BaseHttpResult;
import com.zyc.imitationwechat.test.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpApi {

    @FormUrlEncoded
    @POST("servlet/HelloServlet")
    Observable<BaseHttpResult<User>> login (@Field("m") String m, @Field("name") String name, @Field("pwd") String pwd);

}
