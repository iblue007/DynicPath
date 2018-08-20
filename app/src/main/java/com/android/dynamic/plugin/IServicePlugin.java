package com.android.dynamic.plugin;

import android.content.Context;

/**
 * Created by xuqunxing on 2018/6/25.
 */
public interface IServicePlugin extends IPlugin {
    void setParentContext(Context context);
}