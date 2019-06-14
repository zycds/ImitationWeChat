package com.zyc.imitationwechat.manager;

import android.os.Environment;

public class Global {

    public static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String RECORDER_PATH = SD_PATH + "/recording";


}
