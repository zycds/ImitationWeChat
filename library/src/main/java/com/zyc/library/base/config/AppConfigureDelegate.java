package com.zyc.library.base.config;

import com.billy.android.loading.Gloading;
import com.zyc.library.http.intercept.CustomHeadersInterceptor;
import com.zyc.library.http.intercept.ExceptionInterceptor;
import com.zyc.library.widget.BaseLoadingDialog;

public interface AppConfigureDelegate {

    /**token 异常拦截*/
    ExceptionInterceptor getExceptionInterceptor();

    /**公共Headers*/
    CustomHeadersInterceptor getCustomHeaders();

    /**统一风格的loading dialog*/
    BaseLoadingDialog getBaseLoadingDialog();

    /**统一的状态切换*/
    Gloading.Adapter getGloadingAdapter();

    /**app 网络配置*/
    AppHttpSetting getAppHttpSetting();
    /**app配置*/
    AppSetting getAppSetting();

}
