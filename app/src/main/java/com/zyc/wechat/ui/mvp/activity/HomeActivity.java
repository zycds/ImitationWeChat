package com.zyc.wechat.ui.mvp.activity;

import com.zyc.imitationwechat.R;
import com.zyc.imitationwechat.base.BaseActivity;

import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @Override
    protected void init() {

    }

    @Override
    public void bindView() {
        setContentView(R.layout.activity_home);
        mUnBinder = ButterKnife.bind(this);
    }


}
