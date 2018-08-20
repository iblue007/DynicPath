package com.xxm.dynicpath;

import android.content.Context;
import android.os.Handler;

/**
 * Created by xuqunxing on 2018/6/25.
 */
public class Global {
    private static String APP_ID = BuildConfig.FLAVOR;
    private static String CUID = BuildConfig.FLAVOR;
    private static Context context;
    private static Handler handler;

    public static String getAppID() {
        return "20000073";
    }

    public static Context getContext() {
        return context;
    }

    public static Context getApplicationContext() {
        return context.getApplicationContext();
    }

    public static String getCUID(Context context) {
        return CUID;
    }

    public static void setContext(Context ctx) {
        context = ctx;
    }

    public static void setCUID(String cuid) {
        CUID = cuid;
    }

    public static void setAppId(String appId) {
        APP_ID = appId;
    }

    public static void setHandler(Handler handler) {
        handler = handler;
    }

    public static void runInMainThread(Runnable task) {
        if (task != null && handler != null) {
            handler.post(task);
        }
    }
}