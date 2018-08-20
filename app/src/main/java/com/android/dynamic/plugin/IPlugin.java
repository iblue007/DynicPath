package com.android.dynamic.plugin;

import android.content.Context;

/**
 * Created by xuqunxing on 2018/6/25.
 */
public interface IPlugin {
    void initContext(Context context);

    boolean isDynamicMode();
}