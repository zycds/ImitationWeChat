package com.zyc.imitationwechat.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.zyc.imitationwechat.base.BaseHttpResult;
import com.zyc.imitationwechat.http.ResultHandler;
import com.zyc.imitationwechat.manager.Global;
import com.zyc.imitationwechat.manager.HttpManager;
import com.zyc.imitationwechat.socket.ClientSocket;
import com.zyc.imitationwechat.socket.ClientSocket2;
import com.zyc.imitationwechat.test.User;
import com.zyc.imitationwechat.ui.Main.MainActivity;
import com.zyc.imitationwechat.adapter.MainAdapter;
import com.zyc.imitationwechat.base.BasePresenter;
import com.zyc.imitationwechat.base.IBaseView;
import com.zyc.imitationwechat.utils.ScreenUtils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter {

    private MainAdapter mAdapter;
    private MainActivity mainActivity;

    private String[] mPermissions = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET
    };
    public static final int REQUEST_PERMISSION_CODE = 1000;

    public MainPresenter (IBaseView iBaseView) {
        super(iBaseView);
        mainActivity = (MainActivity) iBaseView;
    }

    public void initRecyclerView () {
        mAdapter = new MainAdapter(mainActivity);
        mainActivity.getRecyclerView().setLayoutManager(mAdapter.getLayoutManager());
        mainActivity.getRecyclerView().setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        Log.i(TAG, "loadData: ");
       /* Disposable wang = HttpManager.getInstance().login("wang", "123123")
                .compose(mainActivity.bindToLifecycle())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User s) throws Exception {
                        Log.i(TAG, "accept: " + s.getName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: " + throwable);
                    }
                });

        HttpManager.getInstance().uploadFile(Global.RECORDER_PATH + "/recordingTemp5146549921301752693mp3");

        File file = new File(Global.RECORDER_PATH + "/recordingTemp5146549921301752693mp3");
        if (file.exists()) {
            HttpManager.getInstance().postFile(file, BaseHttpResult.class, new ResultHandler<BaseHttpResult>(mainActivity) {
                @Override
                public void onBeforeResult() {
                    Log.i(TAG, "onBeforeResult: ");
                }

                @Override
                public void onResult(BaseHttpResult baseHttpResult) {
                    Log.i(TAG, "onResult: ");
                }

                @Override
                public void onAfterFailure() {
                    Log.i(TAG, "onAfterFailure: ");
                }
            });
        }

        ClientSocket2.getInstance().connect();*/

    }

    public void testScreen () {
        Log.i(TAG, "onCreate: " + ScreenUtils.getActionBarHeight(mainActivity) + "   status Height : " + ScreenUtils.getStatusHeight(mainActivity));
        Log.i(TAG, "onCreate: " + ScreenUtils.checkDeviceHasNavigationBar(mainActivity));
        Log.i(TAG, "onCreate: " + ScreenUtils.getNavigationBarHeight(mainActivity));
        Log.i(TAG, "onCreate: " + ScreenUtils.isNavigationBarShow(mainActivity));
        Log.i(TAG, "onCreate: " + ScreenUtils.getScreenWidthHeight(mainActivity)[1]);
    }



    public void requestPermissions (Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permissions = null;
            for (int i = 0; i < mPermissions.length; i++) {
                if (ContextCompat.checkSelfPermission(activity, mPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    if (permissions == null) permissions = mPermissions[i];
                    else permissions  = permissions + "," + mPermissions[i];
                }
            }

            if (permissions != null) {
                activity.requestPermissions(permissions.split(","), REQUEST_PERMISSION_CODE);
            }
        }
    }


}
