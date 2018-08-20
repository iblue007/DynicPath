package com.android.dynamic.plugin;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class PluginApplication extends Application implements IPlugin {
    private Context mPluginContext = null;
    private boolean mPluginMode = false;

    public void onCreate() {
        if (!this.mPluginMode) {
            super.onCreate();
        }
    }

    public boolean isDynamicMode() {
        return this.mPluginMode;
    }

    public void onTerminate() {
        if (!this.mPluginMode) {
            super.onTerminate();
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (!this.mPluginMode) {
            super.onConfigurationChanged(newConfig);
        }
    }

    public void onLowMemory() {
        if (!this.mPluginMode) {
            super.onLowMemory();
        }
    }

    public Context getApplicationContext() {
        if (this.mPluginMode) {
            return this.mPluginContext.getApplicationContext();
        }
        return super.getApplicationContext();
    }

    public void initContext(Context ctx) {
        this.mPluginContext = ctx;
        this.mPluginMode = true;
        attachBaseContext(ctx);
    }
}