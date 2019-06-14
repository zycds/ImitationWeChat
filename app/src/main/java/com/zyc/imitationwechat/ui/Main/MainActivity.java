package com.zyc.imitationwechat.ui.Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zyc.imitationwechat.R;
import com.zyc.imitationwechat.RectCustomButton;
import com.zyc.imitationwechat.base.BaseActivity;
import com.zyc.imitationwechat.manager.AudioManager;
import com.zyc.imitationwechat.presenter.MainPresenter;
import com.zyc.imitationwechat.socket.ClientSocket2;
import com.zyc.wechat.ui.mvp.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//https://www.jianshu.com/p/2e785e64e060
public class MainActivity extends BaseActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rect_voice_btn)
    RectCustomButton rectCustomButton;
    @BindView(R.id.edit_inputText)
    EditText editText;
    @BindView(R.id.image_face)
    ImageView imageFace;

    private Unbinder mBind;
    private MainPresenter mainPresenter;

    @OnClick({R.id.image_voice, R.id.image_face})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_face:
                ClientSocket2.getInstance().send();
                break;
            case R.id.image_voice:
//                changeBottomStatus();
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }

    private void changeBottomStatus () {
        if (editText.getVisibility() == View.GONE) {
            editText.setVisibility(View.VISIBLE);
            rectCustomButton.setVisibility(View.GONE);
        } else {
            editText.setVisibility(View.GONE);
            rectCustomButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void init() {
        mBasePresenter = mainPresenter = new MainPresenter(this);
        mainPresenter.testScreen();
        mainPresenter.requestPermissions(this);
    }

    @Override
    public void bindView() {
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mainPresenter.initRecyclerView();
    }

    @Override
    public void unBindView() {
        if (mBind != null) mBind.unbind();
        AudioManager.getInstance().releaseMediaRecorder(getApplicationContext());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

}
