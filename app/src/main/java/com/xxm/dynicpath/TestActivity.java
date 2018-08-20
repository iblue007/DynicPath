package com.xxm.dynicpath;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.dynamic.plugin.PluginActivity;

public class TestActivity extends PluginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void toast(View view){
        Toast.makeText(this,"dsfjalksjfdklajslkdfjlakdsf",1).show();
    }
}
