package com.zyc.imitationwechat.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zyc.imitationwechat.MainActivity;
import com.zyc.imitationwechat.adapter.MainAdapter;
import com.zyc.imitationwechat.base.BasePresenter;
import com.zyc.imitationwechat.base.IBaseView;
import com.zyc.imitationwechat.utils.ScreenUtils;

public class MainPresenter extends BasePresenter {

    private MainAdapter mAdapter;
    private MainActivity mainActivity;
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
    }

    public void testScreen () {
        Log.i(TAG, "onCreate: " + ScreenUtils.getActionBarHeight(mainActivity) + "   status Height : " + ScreenUtils.getStatusHeight(mainActivity));
        Log.i(TAG, "onCreate: " + ScreenUtils.checkDeviceHasNavigationBar(mainActivity));
        Log.i(TAG, "onCreate: " + ScreenUtils.getNavigationBarHeight(mainActivity));
        Log.i(TAG, "onCreate: " + ScreenUtils.isNavigationBarShow(mainActivity));
        Log.i(TAG, "onCreate: " + ScreenUtils.getScreenWidthHeight(mainActivity)[1]);
    }


}
