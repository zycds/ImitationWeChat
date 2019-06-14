package com.zyc.imitationwechat.manager;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class AudioManager {

    public static final String TAG = AudioManager.class.getSimpleName();

    private MediaRecorder sMediaRecorder;
    public String sOutPutTempPath;

    private static AudioManager instance;
    public static AudioManager getInstance () {
        if (instance == null) {
            synchronized (AudioManager.class) {
                if (instance == null) {
                    instance = new AudioManager();
                }
            }
        }
        return instance;
    }

    public void recordMediaRecorder () {
        if (sMediaRecorder == null) {
            sMediaRecorder = new MediaRecorder();
        }
        sMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        sMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        sMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File file = new File(Global.RECORDER_PATH);
        if (!file.exists()) file.mkdirs();
        try {
            File audioFile = File.createTempFile("recordingTemp", ".tmp", file);
            sOutPutTempPath = audioFile.getAbsolutePath();
            sMediaRecorder.setOutputFile(sOutPutTempPath);
            sMediaRecorder.prepare();
            sMediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void stopMediaRecorder () {
        if (sMediaRecorder == null) return;
        sMediaRecorder.stop();
        sMediaRecorder.reset();
        sOutPutTempPath = null;
    }

    public void releaseMediaRecorder (Context context) {
        if (sMediaRecorder != null) sMediaRecorder.release();
        sMediaRecorder = null;
        if (sOutPutTempPath != null) {
            playVoice(context, sOutPutTempPath);
            sOutPutTempPath = null;
        }
    }

    public void playVoice (Context context,String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return;
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.parse(filePath));
        mediaPlayer.setOnCompletionListener(mp -> {
            Log.i(TAG, "onCompletion: ");
            mp.release();
        });
        mediaPlayer.start();
    }

}
