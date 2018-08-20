package com.android.dynamic.plugin;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import com.android.dynamic.Exception.MethodInvokeErrorException;

import java.util.List;

public class PluginPendingIntentHelper {
    private static final String NOTIFICATION_RECEIVER_ACTION = "com.baidu.android.action.RECEIVE_NOTIFICATION";
    private static final String NOTIFY_TYPE = "notify_type";
    private static final int PENDING_TYPE_ACTIVITY = 1;
    private static final int PENDING_TYPE_SERVICE = 2;

    public static PendingIntent getActivity(Context context, int requestCode, Intent intent, int flags) {
        List<ResolveInfo> resolveInfo = context.getPackageManager().queryIntentActivities(intent, 65536);
        if (resolveInfo != null && resolveInfo.size() > 0) {
            return PendingIntent.getActivity(context, requestCode, intent, flags);
        }
        Intent newIntent = assembleCommonIntent(context, intent);
        newIntent.putExtra(NOTIFY_TYPE, PENDING_TYPE_ACTIVITY);
        return PendingIntent.getBroadcast(context, requestCode, newIntent, flags);
    }

    public static PendingIntent getService(Context context, int requestCode, Intent intent, int flags) {
        List<ResolveInfo> resolveInfo = context.getPackageManager().queryIntentServices(intent, 65536);
        if (resolveInfo != null && resolveInfo.size() > 0) {
            return PendingIntent.getService(context, requestCode, intent, flags);
        }
        Intent newIntent = assembleCommonIntent(context, intent);
        newIntent.putExtra(NOTIFY_TYPE, PENDING_TYPE_SERVICE);
        return PendingIntent.getBroadcast(context, requestCode, newIntent, flags);
    }

    public static PendingIntent getBroadcast(Context context, int requestCode, Intent intent, int flags) {
        return PendingIntent.getBroadcast(context, requestCode, intent, flags);
    }

    private static Intent assembleCommonIntent(Context context, Intent intent) {
        if (context instanceof IActivityPlugin) {
            Intent newIntent;
            if (intent == null) {
                newIntent = new Intent(NOTIFICATION_RECEIVER_ACTION);
            } else {
                newIntent = (Intent) intent.clone();
                newIntent.setAction(NOTIFICATION_RECEIVER_ACTION);
                newIntent.setComponent(null);
            }
            newIntent.putExtra(PluginActivity.IS_EXPLICIT_INTENT, true);
            newIntent.putExtra(PluginActivity.PLUGIN_PKG_NAME, ((IActivityPlugin) context).getPluginPackageName());
            newIntent.putExtra("pluginLoaderActivity.MainClassName", intent.getComponent() == null ? BuildConfig.FLAVOR : intent.getComponent().getClassName());
            newIntent.setFlags(268435456);
            return newIntent;
        }
        throw new MethodInvokeErrorException();
    }
}