package com.xxm.dynicpath;

import android.content.Intent;
import android.os.IBinder;

import com.android.dynamic.plugin.PluginService;

/**
 * Created by xuqunxing on 2018/8/16.
 */

public class MyService extends PluginService{

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * 服务创建的时候调用
     */
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        System.out.println("=========onCreate======");
    }
    /**
     * 服务启动的时候调用
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        System.out.println("=========onStartCommand======");
        return super.onStartCommand(intent, flags, startId);
    }
    /**
     * 服务销毁的时候调用
     */
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        System.out.println("=========onDestroy======");
        super.onDestroy();
    }
}
