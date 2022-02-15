
package com.androidinstalledapps;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;

import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

import javax.annotation.Nullable;

import com.helper.*;

public class RNAndroidInstalledAppsModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNAndroidInstalledAppsModule(final ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAndroidInstalledApps";
  }

  @ReactMethod
  public void getApps(final Promise promise) {
    class OneShotTask implements Runnable {
      private final ReactApplicationContext reactContext;

      OneShotTask(final ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
      }
      public void run() {
        try {
          final PackageManager pm = this.reactContext.getPackageManager();
          final List<PackageInfo> pList = pm.getInstalledPackages(0);
          final WritableArray list = Arguments.createArray();
          for (int i = 0; i < pList.size(); i++) {
            final PackageInfo packageInfo = pList.get(i);
            final WritableMap appInfo = Arguments.createMap();

            appInfo.putString("packageName", packageInfo.packageName);
            appInfo.putString("versionName", packageInfo.versionName);
            appInfo.putDouble("versionCode", packageInfo.versionCode);
            appInfo.putDouble("firstInstallTime", (packageInfo.firstInstallTime));
            appInfo.putDouble("lastUpdateTime", (packageInfo.lastUpdateTime));
            appInfo.putString("appName", ((String) packageInfo.applicationInfo.loadLabel(pm)).trim());

            final Drawable icon = pm.getApplicationIcon(packageInfo.applicationInfo);
            appInfo.putString("icon", Utility.convert(icon));

            final String apkDir = packageInfo.applicationInfo.publicSourceDir;
            appInfo.putString("apkDir", apkDir);

            final File file = new File(apkDir);
            final double size = file.length();
            appInfo.putDouble("size", size);

            list.pushMap(appInfo);
          }
          promise.resolve(list);
        } catch (final Exception ex) {
          promise.reject(ex);
        }
      }
    }
    Thread t = new Thread(new OneShotTask(this.reactContext));
    t.start();
  }

  @ReactMethod
  public void getNonSystemApps(final Promise promise) {
    class OneShotTask implements Runnable {
      private final ReactApplicationContext reactContext;

      OneShotTask(final ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
      }

      public void run() {
        try {
          final PackageManager pm = this.reactContext.getPackageManager();
          final List<PackageInfo> pList = pm.getInstalledPackages(0);
          final WritableArray list = Arguments.createArray();
          for (int i = 0; i < pList.size(); i++) {
            final PackageInfo packageInfo = pList.get(i);
            final WritableMap appInfo = Arguments.createMap();

            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
              appInfo.putString("packageName", packageInfo.packageName);
              appInfo.putString("versionName", packageInfo.versionName);
              appInfo.putDouble("versionCode", packageInfo.versionCode);
              appInfo.putDouble("firstInstallTime", (packageInfo.firstInstallTime));
              appInfo.putDouble("lastUpdateTime", (packageInfo.lastUpdateTime));
              appInfo.putString("appName", ((String) packageInfo.applicationInfo.loadLabel(pm)).trim());

              final Drawable icon = pm.getApplicationIcon(packageInfo.applicationInfo);
              appInfo.putString("icon", Utility.convert(icon));

              final String apkDir = packageInfo.applicationInfo.publicSourceDir;
              appInfo.putString("apkDir", apkDir);

              final File file = new File(apkDir);
              final double size = file.length();
              appInfo.putDouble("size", size);

              list.pushMap(appInfo);
            }
          }
          promise.resolve(list);
        } catch (final Exception ex) {
          promise.reject(ex);
        }

      }
    }
    Thread t = new Thread(new OneShotTask(this.reactContext));
    t.start();

  }

  @ReactMethod
  public void getNonSystemAppNames(final Promise promise) {
    class OneShotTask implements Runnable {
      private final ReactApplicationContext reactContext;

      OneShotTask(final ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
      }

      public void run() {
        try {
          final PackageManager pm = this.reactContext.getPackageManager();
          final List<PackageInfo> pList = pm.getInstalledPackages(0);
          final WritableArray list = Arguments.createArray();
          for (int i = 0; i < pList.size(); i++) {
            final PackageInfo packageInfo = pList.get(i);
            final WritableMap appInfo = Arguments.createMap();

            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
              appInfo.putString("packageName", packageInfo.packageName);              

              list.pushMap(appInfo);
            }
          }
          promise.resolve(list);
        } catch (final Exception ex) {
          promise.reject(ex);
        }

      }
    }
    Thread t = new Thread(new OneShotTask(this.reactContext));
    t.start();

  }

  @ReactMethod
  public void getSystemApps(final Promise promise) {
    class OneShotTask implements Runnable {
      private final ReactApplicationContext reactContext;

      OneShotTask(final ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
      }
      public void run(){
        try {
          final PackageManager pm = this.reactContext.getPackageManager();
          final List<PackageInfo> pList = pm.getInstalledPackages(0);
          final WritableArray list = Arguments.createArray();
          for (int i = 0; i < pList.size(); i++) {
            final PackageInfo packageInfo = pList.get(i);
            final WritableMap appInfo = Arguments.createMap();

            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
              appInfo.putString("packageName", packageInfo.packageName);
              appInfo.putString("versionName", packageInfo.versionName);
              appInfo.putDouble("versionCode", packageInfo.versionCode);
              appInfo.putDouble("firstInstallTime", (packageInfo.firstInstallTime));
              appInfo.putDouble("lastUpdateTime", (packageInfo.lastUpdateTime));
              appInfo.putString("appName", ((String) packageInfo.applicationInfo.loadLabel(pm)).trim());

              final Drawable icon = pm.getApplicationIcon(packageInfo.applicationInfo);
              appInfo.putString("icon", Utility.convert(icon));

              final String apkDir = packageInfo.applicationInfo.publicSourceDir;
              appInfo.putString("apkDir", apkDir);

              final File file = new File(apkDir);
              final double size = file.length();
              appInfo.putDouble("size", size);

              list.pushMap(appInfo);
            }
          }
          promise.resolve(list);
        } catch (final Exception ex) {
          promise.reject(ex);
        }
      }
    }
  }
}
