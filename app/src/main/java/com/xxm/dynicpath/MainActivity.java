package com.xxm.dynicpath;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.dynamic.plugin.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump(View view){
        int pluginUpgradeConfigVersion = BaseConfigPreferences.getInstance(Global.getContext()).getPluginUpgradeConfigVersion();
        Log.e("======","======pluginUpgradeConfigVersion:"+pluginUpgradeConfigVersion);
        BaseConfigPreferences.getInstance(Global.getContext()).setPluginUpgradeConfigVersion(pluginUpgradeConfigVersion + 1 );
        startActivity(new Intent(this,TestActivity.class));
    }

    public void starService(View view){
        Intent startIntent = new Intent(this, MyService.class);
        startService(startIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent stopIntent = new Intent(this, MyService.class);
        stopService(stopIntent);
    }
}
