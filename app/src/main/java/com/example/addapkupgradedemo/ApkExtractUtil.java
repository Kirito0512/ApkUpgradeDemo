package com.example.addapkupgradedemo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

public class ApkExtractUtil {
    public static String extract(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String apkPath = applicationInfo.sourceDir;
        Log.d("xuqi", "apkpath = " + apkPath);
        return apkPath;
    }
}
