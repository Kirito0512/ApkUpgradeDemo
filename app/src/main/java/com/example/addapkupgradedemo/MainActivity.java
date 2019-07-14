package com.example.addapkupgradedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.textView);
        tv.setText(getStringFromJNI());
        ApkExtractUtil.extract(this);
    }

    public native String getStringFromJNI();
    public static native int bspatch(String oldApk, String newApk, String patch);
}
