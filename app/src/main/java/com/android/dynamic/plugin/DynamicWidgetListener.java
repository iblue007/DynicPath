package com.android.dynamic.plugin;

/**
 * Created by xuqunxing on 2018/6/25.
 */

public interface DynamicWidgetListener {
    void onDestory(int i);

    void onLauncherInvoke(int i, int i2);

    void onLoad(int i);
}
