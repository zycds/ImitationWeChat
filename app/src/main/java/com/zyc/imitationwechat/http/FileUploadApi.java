package com.zyc.imitationwechat.http;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface FileUploadApi {

    @Multipart
    @POST("servlet/FileUploadServlet")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);

    /**
     * 上传文件请求
     * @param paramMap 请求参数
     * @return
     */
    @Multipart
    @POST("servlet/FileUploadServlet")
    Call<ResponseBody> postFile(@PartMap Map<String, RequestBody> paramMap);
}
