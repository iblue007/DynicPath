package com.android.dynamic.plugin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.io.File;
import java.util.List;

public class PluginActivity extends Activity implements IActivityPlugin {
    public static final String IS_EXPLICIT_INTENT = "is_explicit_intent";
    public static final String PLUGIN_PKG_NAME = "plugin_pkg_name";
    private LayoutInflater mLayoutInflater;
    private View mPluginContentView;
    private Context mPluginContext = null;
    private Activity mPluginLoaderActivity;
    private boolean mPluginMode = false;
    protected String mPluginPackageName = BuildConfig.FLAVOR;

    public void setParentContext(Activity activity) {
        this.mPluginLoaderActivity = activity;
    }

    public String getPluginPackageName() {
        return this.mPluginPackageName;
    }

    public void initContext(Context ctx) {
        this.mPluginContext = ctx;
        this.mPluginMode = true;
        attachBaseContext(ctx);
    }

    public boolean isDynamicMode() {
        return this.mPluginMode;
    }

    public void submitEvent(Context context, int eventId) {
        submitEvent(this.mPluginContext, eventId, BuildConfig.FLAVOR);
    }

    public void submitEvent(Context context, int eventId, String label) {
        PluginUtil.invokeSubmitEvent(this.mPluginLoaderActivity, eventId, label);
    }

    public View findViewById(int id) {
        if (this.mPluginContentView == null || !this.mPluginMode) {
            return super.findViewById(id);
        }
        return this.mPluginContentView.findViewById(id);
    }

    public void finish() {
        if (this.mPluginMode) {
            this.mPluginLoaderActivity.finish();
        } else {
            super.finish();
        }
    }

    public int getChangingConfigurations() {
        if (this.mPluginMode) {
            return this.mPluginLoaderActivity.getChangingConfigurations();
        }
        return super.getChangingConfigurations();
    }

    public LayoutInflater getLayoutInflater() {
        if (this.mPluginMode) {
            return getDynamicInflate(this.mPluginContext);
        }
        return super.getLayoutInflater();
    }

    public MenuInflater getMenuInflater() {
        if (this.mPluginContext == null || !this.mPluginMode) {
            return super.getMenuInflater();
        }
        return new MenuInflater(this.mPluginContext);
    }

    protected void onCreate(Bundle savedInstanceState) {
        if (!this.mPluginMode) {
            super.onCreate(savedInstanceState);
        }
    }

    public SharedPreferences getSharedPreferences(String name, int mode) {
        if (this.mPluginMode) {
            return this.mPluginContext.getSharedPreferences(name, mode);
        }
        return super.getSharedPreferences(name, mode);
    }

//    public File getSharedPrefsFile(String name) {
//        if (this.mPluginMode) {
//            return this.mPluginContext.getSharedPrefsFile(name);
//        }
//        return super.getSharedPrefsFile(name);
//    }

    public Object getSystemService(String name) {
        if (!this.mPluginMode) {
            return super.getSystemService(name);
        }
        if ("window".equals(name) || "search".equals(name)) {
            return this.mPluginLoaderActivity.getSystemService(name);
        }
        if ("layout_inflater".equals(name)) {
            return getDynamicInflate(this.mPluginContext);
        }
        return this.mPluginContext.getSystemService(name);
    }

    public Window getWindow() {
        if (this.mPluginMode) {
            return this.mPluginLoaderActivity.getWindow();
        }
        return super.getWindow();
    }

    public WindowManager getWindowManager() {
        if (this.mPluginMode) {
            return this.mPluginLoaderActivity.getWindowManager();
        }
        return super.getWindowManager();
    }

    protected void onStart() {
        if (!this.mPluginMode) {
            super.onStart();
        }
    }

    protected void onPause() {
        if (!this.mPluginMode) {
            super.onPause();
        }
    }

    protected void onStop() {
        if (!this.mPluginMode) {
            super.onStop();
        }
    }

    protected void onResume() {
        if (!this.mPluginMode) {
            super.onResume();
        }
    }

    protected void onDestroy() {
        if (!this.mPluginMode) {
            super.onDestroy();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mPluginMode) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        if (this.mPluginMode) {
            return false;
        }
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.mPluginMode) {
            return false;
        }
        return super.onKeyUp(keyCode, event);
    }

    public View getCurrentFocus() {
        if (this.mPluginMode) {
            return this.mPluginLoaderActivity.getCurrentFocus();
        }
        return super.getCurrentFocus();
    }

    public void onBackPressed() {
        if (this.mPluginMode) {
            this.mPluginLoaderActivity.finish();
        } else {
            super.onBackPressed();
        }
    }

    public void setContentView(int layoutResID) {
        if (this.mPluginMode) {
            this.mPluginContentView = getDynamicInflate(this.mPluginContext).inflate(layoutResID, null);
            this.mPluginLoaderActivity.setContentView(this.mPluginContentView);
            return;
        }
        super.setContentView(layoutResID);
    }

    private LayoutInflater getDynamicInflate(Context ctx) {
        if (this.mLayoutInflater != null) {
            return this.mLayoutInflater;
        }
        this.mLayoutInflater = LayoutInflater.from(ctx).cloneInContext(this);
        return this.mLayoutInflater;
    }

    public void setContentView(View view) {
        if (this.mPluginMode) {
            this.mPluginContentView = view;
            this.mPluginLoaderActivity.setContentView(view);
            return;
        }
        super.setContentView(view);
    }

    public void startActivity(Intent intent) {
        if (this.mPluginMode) {
            startActivityForResult(intent, -1);
        } else {
            super.startActivity(intent);
        }
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        if (this.mPluginMode) {
            List<ResolveInfo> resolveInfo = this.mPluginLoaderActivity.getPackageManager().queryIntentActivities(intent, 65536);
            if (resolveInfo == null || resolveInfo.size() <= 0) {
                intent.putExtra(PLUGIN_PKG_NAME, this.mPluginPackageName);
                intent.putExtra(IS_EXPLICIT_INTENT, true);
            }
            if (requestCode >= 0) {
                this.mPluginLoaderActivity.startActivityForResult(intent, requestCode);
                return;
            } else {
                this.mPluginLoaderActivity.startActivity(intent);
                return;
            }
        }
        super.startActivityForResult(intent, requestCode);
    }

    public ComponentName startService(Intent service) {
        if (!this.mPluginMode) {
            return super.startService(service);
        }
        List<ResolveInfo> resolveInfo = this.mPluginLoaderActivity.getPackageManager().queryIntentServices(service, 65536);
        if (resolveInfo == null || resolveInfo.size() <= 0) {
            service.putExtra(PLUGIN_PKG_NAME, this.mPluginPackageName);
            service.putExtra(IS_EXPLICIT_INTENT, true);
        }
        return this.mPluginLoaderActivity.startService(service);
    }

    public boolean stopService(Intent name) {
        if (!this.mPluginMode) {
            return super.stopService(name);
        }
        List<ResolveInfo> resolveInfo = this.mPluginLoaderActivity.getPackageManager().queryIntentServices(name, 65536);
        if (resolveInfo == null || resolveInfo.size() <= 0) {
            name.putExtra(PLUGIN_PKG_NAME, this.mPluginPackageName);
            name.putExtra(IS_EXPLICIT_INTENT, true);
        }
        return this.mPluginLoaderActivity.stopService(name);
    }

//    public Cursor managedQueryReplaced(Uri uri, String[] projection, String selection, String sortOrder) {
//        if (this.mPluginMode) {
//            return this.mPluginLoaderActivity.managedQuery(uri, projection, selection, sortOrder);
//        }
//        return super.managedQuery(uri, projection, selection, sortOrder);
//    }

    public void setResultReplaced(int resultCode, Intent data) {
        if (this.mPluginMode) {
            this.mPluginLoaderActivity.setResult(resultCode, data);
        } else {
            setResult(resultCode, data);
        }
    }

    public void setResultReplaced(int resultCode) {
        if (this.mPluginMode) {
            this.mPluginLoaderActivity.setResult(resultCode);
        } else {
            setResult(resultCode);
        }
    }

    public Cursor managedQueryReplaced(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (this.mPluginMode) {
            return this.mPluginLoaderActivity.managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        }
        return super.managedQuery(uri, projection, selection, selectionArgs, sortOrder);
    }
}