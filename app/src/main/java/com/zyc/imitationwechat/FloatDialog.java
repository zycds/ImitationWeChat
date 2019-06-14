package com.zyc.imitationwechat;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyc.imitationwechat.manager.AudioManager;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FloatDialog extends Dialog {

    @BindView(R.id.volume)
    ImageView volume;
    @BindView(R.id.layout_speaking)
    LinearLayout layoutSpeaking;
    @BindView(R.id.image_want_cancel)
    ImageView imageWantCancel;
    @BindView(R.id.text_tips)
    TextView textTips;

    private Context mContext;
    private Unbinder mBinder;
    private Handler mHandler;

    public static final int SPACE_UPDATE_VOLUME_TIME = 1000;

    public FloatDialog(@NonNull Context context) {
        this(context, 0);
    }

    public FloatDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void showSpeakingDialog() {
        if (!isShowing()) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_float, null);
            mBinder = ButterKnife.bind(this, inflate);
            setContentView(inflate);
            show();
        }
        AudioManager.getInstance().recordMediaRecorder();
        setIsShowVolumeOrCancel(false);
        textTips.setText(R.string.speaking);
        mHandler.postDelayed(volumeRunnable, SPACE_UPDATE_VOLUME_TIME);
    }

    public void showWantToCancel() {
        mHandler.removeCallbacks(volumeRunnable);
        setIsShowVolumeOrCancel(true);
        textTips.setText(R.string.want_to_cancel);
        AudioManager.getInstance().stopMediaRecorder();
    }

    private void setIsShowVolumeOrCancel (boolean isCancel) {
        if (isCancel) {
            layoutSpeaking.setVisibility(View.GONE);
            imageWantCancel.setVisibility(View.VISIBLE);
        } else {
            layoutSpeaking.setVisibility(View.VISIBLE);
            imageWantCancel.setVisibility(View.GONE);
        }
    }

    private void updateVolume () {
        int v = getVolume();
        int drawableId = mContext.getResources().getIdentifier("ic_signal_wifi_40dp_" + v, "drawable", mContext.getPackageName());
        volume.setImageResource(drawableId);
    }

    public void dismissDialog() {
        if (mBinder!= null) mBinder.unbind();
        mHandler.removeCallbacks(volumeRunnable);
        AudioManager.getInstance().releaseMediaRecorder(mContext);
        dismiss();
    }

    private int getVolume() {
        Random random = new Random();
        return random.nextInt(5);
    }

    private VolumeRunnable volumeRunnable = new VolumeRunnable();
    class VolumeRunnable implements Runnable {

        @Override
        public void run() {
            updateVolume();
            mHandler.postDelayed(volumeRunnable, SPACE_UPDATE_VOLUME_TIME);
        }
    }

}
