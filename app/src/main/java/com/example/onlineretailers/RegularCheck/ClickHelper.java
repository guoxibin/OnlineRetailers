package com.example.onlineretailers.RegularCheck;

import android.text.TextUtils;

import androidx.annotation.MainThread;

public class ClickHelper {
    private static long lastClickTime = 0;
    private static String lastButtonId = null;

    /**
     * 判断事件出发时间间隔是否超过预定值
     * 如果小于间隔（目前是1000毫秒）则返回true，否则返回false
     */
    @MainThread
    public static boolean isFastDoubleClick(final String buttonId) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (TextUtils.equals(lastButtonId, buttonId) && 0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }
}
