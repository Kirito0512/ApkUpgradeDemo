package com.example.addapkupgradedemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static int REQUEST_PERMISSION_CODE = 1;
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("bspatch");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.textView);
        tv.setText(String.format("%s_old_new", getStringFromJNI()));
        ApkExtractUtil.extract(this);
        Log.d("xuqi", "Environment.getExternalStorageDirectory() = " + Environment.getExternalStorageDirectory());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
                } else {
                    doPatch();
                }
            }
        });
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.detectFileUriExposure();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            doPatch();
        } else {
            Log.d("xuqi", "请求权限失败");
        }
    }

    private void doPatch() {
        final File destNewApk = new File(Environment.getExternalStorageDirectory(), "app-debug-new.apk");
        File patchFile = new File(Environment.getExternalStorageDirectory(), "patchFile.patch");
//        检查文件存在
        bspatch(ApkExtractUtil.extract(this), destNewApk.getAbsolutePath(), patchFile.getAbsolutePath());
        if (destNewApk.exists()) {
            ApkExtractUtil.install(this, destNewApk.getAbsolutePath());
        }
    }

    public native String getStringFromJNI();
    public static native int bspatch(String oldApk, String newApk, String patch);
}
