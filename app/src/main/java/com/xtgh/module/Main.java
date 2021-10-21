package com.xtgh.module;

import com.xtgh.module.Core.PreHook;
import com.xtgh.module.Utilities.Utils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {
    public static final String TAG = "xTGH";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        String currentPackageName = lpparam.packageName;
        if (currentPackageName.equals(Utils.TGH_PACKAGE_NAMES[0]) || currentPackageName.equals(Utils.TGH_PACKAGE_NAMES[1]) || currentPackageName.equals(Utils.TGH_PACKAGE_NAMES[2])) {
            new PreHook(lpparam.classLoader);
        }
    }
}