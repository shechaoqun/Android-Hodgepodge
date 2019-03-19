package com.example.hookback;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 3/11/19
 * shechaoqun@gmail.com
 */

public class Moudle implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if (loadPackageParam.packageName.equals("com.example.hooktarget")) {

            XposedBridge.log("Loaded app:" + loadPackageParam.packageName);

            XposedHelpers.findAndHookMethod("com.example.hooktarget.MainActivity",
                    loadPackageParam.classLoader,
                    "isOK",
                    String.class,
                    String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("username" + (String) param.args[0] + "   password:" + (String) param.args[1]);
                            Log.d("zz","账号："+(String)param.args[0]+"   密码："+(String)param.args[1]);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("hook end");
                            Log.d("zz", param.getResult().toString());
                        }
                    });

        }


    }
}
