package com.android.dynamic.plugin;

import android.content.Context;
import java.lang.reflect.Method;

public class PluginUtil {
    private static final String ANALYTICS_METHOD = "onEvent";
    private static final String ND_ANALYTICS = "com.nd.analytics.NdAnalytics";

    public static void invokeSubmitEvent(Context context, int eventId, String label) {
        try {
            Class<?> clazz = context.getClassLoader().loadClass(ND_ANALYTICS);
            Method m = clazz.getDeclaredMethod(ANALYTICS_METHOD, new Class[]{Context.class, Integer.TYPE, String.class});
            m.setAccessible(true);
            m.invoke(clazz, new Object[]{context, Integer.valueOf(eventId), label});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}