package com.xtgh.module.Core;

import com.xtgh.module.Utilities.Utils;
import com.xtgh.module.Utilities.Versions;

public class PreHook {
    public PreHook(ClassLoader classLoader) {
        String versionCode = Utils.getVersionCode(classLoader);
        Hooks hooks = new Hooks(classLoader);
        /*
         * Version not detected.
         * Use latest available approach.
         * */
        if (versionCode == null) hooks.T812_P91();
        else if (versionCode.equals(Versions.T812))
            hooks.T812_P91();
        else if (versionCode.equals(Versions.T800))
            hooks.T800_P912_T800_P92();
        else if (versionCode.equals(Versions.T793) || versionCode.equals(Versions.T791))
            hooks.T793_P912_T793_P911_T791_P91();
        else if (versionCode.equals(Versions.T782))
            hooks.T782_P903_T781_P902();
        else if (versionCode.equals(Versions.T780)) {
            /*
             * Multiple hook without consequences.
             * */
            hooks.T780_P90_T780_P901();
            hooks.T782_P903_T781_P902();
        } else if (versionCode.equals(Versions.T773)) {
            hooks.T772_P89();
            hooks.T772_P891_T772_P892_T772_P893();
        } else if (versionCode.equals(Versions.T760))
            hooks.T760_P88_T760_P881_T760_P882_T760_P883();
    }
}
