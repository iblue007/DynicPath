package com.android.dynamic.plugin;

import android.app.Activity;

/**
 * Created by xuqunxing on 2018/6/25.
 */

public interface IActivityPlugin extends IPlugin {
    String getPluginPackageName();

    void setParentContext(Activity activity);
}