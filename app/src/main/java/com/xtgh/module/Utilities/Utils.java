package com.xtgh.module.Utilities;

import static de.robv.android.xposed.XposedHelpers.findClassIfExists;
import static de.robv.android.xposed.XposedHelpers.findFieldIfExists;

import java.lang.reflect.Field;

public class Utils {
    public static String[] TGH_PACKAGE_NAMES = {"ir.ilmili.telegraph", "ir.ilmili.telegraph.second", "ir.ilmili.telegraph.third"};

    public static String getVersionCode(ClassLoader classLoader) {
        String versionCode = null;
        Field field;
        try {
            Class<?> buildVars = findClassIfExists("org.telegram.messenger.BuildVars", classLoader);
            if (buildVars != null) {
                field = findFieldIfExists(buildVars, "h");
                if (fieldIsVersionCode(field)) {
                    versionCode = (String) field.get(null);
                } else {
                    /*
                     * Maybe it's below version T7.8.0
                     * */
                    field = findFieldIfExists(buildVars, "g");
                    if (fieldIsVersionCode(field)) {
                        versionCode = (String) field.get(null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * @return {@code true} if the given field is version field and it's also a supported version, {@code false} otherwise.
     */
    private static boolean fieldIsVersionCode(Field field) {
        try {
            if (field != null) {
                Object fieldValue = field.get(null);
                if (fieldValue instanceof String) {
                    String possibleVersion = (String) fieldValue;
                    if (isMatchAnySupportedVersion(possibleVersion)) return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private static boolean isMatchAnySupportedVersion(String version) {
        return (version.equals(Versions.T812) || version.equals(Versions.T800) ||
                version.equals(Versions.T793) || version.equals(Versions.T791) ||
                version.equals(Versions.T782) || version.equals(Versions.T781) ||
                version.equals(Versions.T780) || version.equals(Versions.T773) ||
                version.equals(Versions.T772) || version.equals(Versions.T760));
    }
}
