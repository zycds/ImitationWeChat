package com.zyc.imitationwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import java.lang.reflect.Method;

public class ScreenUtils {

    public static final String TAG = ScreenUtils.class.getSimpleName();

    /**
     *  get action bar height
     * @return pixel
     */
    public static int getActionBarHeight (Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
            return actionBarHeight;
        }
        return 0;
    }

    /**
     *  get status bar height
     * @return pixel
     */
    public static int getStatusHeight (Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0 ) return context.getResources().getDimensionPixelSize(resourceId);
        return 0;
    }

    /**
     *  get status bar height
     * @return pixel
     */
    public static int getNavigationBarHeight (Context context) {
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0 ) return context.getResources().getDimensionPixelSize(resourceId);
        return 0;
    }

    /**
     * judge can show navigation bar
     * @param context
     * @return true  can show
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
            Log.e(TAG, "checkDeviceHasNavigationBar: " + e);
            hasNavigationBar = false;
        }
        return hasNavigationBar;
    }

    /**
     * @param context
     * @return  current status is show ?  true is show
     */
    public static boolean isNavigationBarShow (Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = context.getWindowManager().getDefaultDisplay();
            Point point = new Point();
            Point realPoint = new Point();
            display.getSize(point);
            display.getRealSize(realPoint);
            return point.y != realPoint.y;
        } else {
            boolean hasMenu = ViewConfiguration.get(context).hasPermanentMenuKey();
            boolean hasBack = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            return hasBack || hasMenu;
        }
    }

    /**
     * get screen width , height
     * @param context
     * @return width height
     */
    public static int[] getScreenWidthHeight (Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;
        return new int[]{w, h};
    }

}
