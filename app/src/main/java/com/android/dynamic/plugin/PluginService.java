package com.android.dynamic.plugin;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class PluginService extends Service implements IServicePlugin {
    private Context mPluginContext = null;
    private boolean mPluginMode = false;
    protected Service parentContext;
    protected Handler parentHandler;

    public void initContext(Context ctx) {
        this.mPluginContext = ctx;
        this.mPluginMode = true;
        attachBaseContext(this.mPluginContext);
    }

    public boolean isDynamicMode() {
        return this.mPluginMode;
    }

    public void setParentContext(Context ctx) {
        this.parentContext = (Service) ctx;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        if (!this.mPluginMode) {
            super.onCreate();
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (this.mPluginMode) {
            return 9999;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        if (!this.mPluginMode) {
            super.onDestroy();
        }
    }

    public ComponentName startService(Intent service) {
        if (this.mPluginMode) {
            return this.mPluginContext.startService(service);
        }
        return super.startService(service);
    }

    public boolean stopService(Intent name) {
        if (this.mPluginMode) {
            return this.mPluginContext.stopService(name);
        }
        return super.stopService(name);
    }
}