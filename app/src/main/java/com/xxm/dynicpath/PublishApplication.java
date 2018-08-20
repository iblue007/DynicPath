package com.xxm.dynicpath;

import android.os.Handler;

import com.android.dynamic.plugin.PluginApplication;

/**
 * Created by xuqunxing on 2018/6/25.
 */
public class PublishApplication extends PluginApplication {
    public void onCreate() {
        super.onCreate();
        Global.setContext(this);
        Global.setAppId(getPackageName());
        Global.setHandler(new Handler());
        Global.setCUID(BuildConfig.FLAVOR);
    }
}