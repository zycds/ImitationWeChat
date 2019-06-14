package com.zyc.imitationwechat.http;

import android.content.Context;
import android.widget.Toast;

import com.zyc.imitationwechat.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * 网络请求结果处理类
 * @param <T> 请求结果封装对象
 */
public  abstract class ResultHandler<T> {
    Context context;

    public ResultHandler(Context context) {
        this.context = context;
    }

    /**
     * 判断网络是否未连接
     *
     * @return
     */
    public boolean isNetDisconnected() {
        return false;
    }

    /**
     * 请求成功之前
     */
    public abstract void onBeforeResult();

    /**
     * 请求成功时
     *
     * @param t 结果数据
     */
    public abstract void onResult(T t);

    /**
     * 服务器出错
     */
    public void onServerError() {
        // 服务器处理出错
        Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();
    }

    /**
     * 请求失败后的处理
     */
    public abstract void onAfterFailure();

    /**
     * 请求失败时的处理
     *
     * @param t
     */
    public void onFailure(Throwable t) {
        if (t instanceof SocketTimeoutException || t instanceof ConnectException) {
            // 连接异常
            if (isNetDisconnected()) {
                // 服务器连接出错
                Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();
            } else {
                // 手机网络不通
                Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();
            }
        } else if (t instanceof Exception) {
            // 功能异常
            Toast.makeText(context, R.string.app_name, Toast.LENGTH_SHORT).show();
        }
    }
}
