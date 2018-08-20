package com.android.dynamic.plugin;

import android.app.LocalActivityManager;

/**
 * Created by xuqunxing on 2018/6/25.
 */
public interface IActivityGroupPlugin extends IActivityPlugin {
    void setActivityManager(LocalActivityManager localActivityManager);
}
