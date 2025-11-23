package org.saucelabs.utilty.driverUtils;

public class PlatformUtils
{
    public static String getPlatFormName()
    {
        String platfrom = ConfigReader.get("platform").toLowerCase();
        return platfrom;
    }

    public static boolean isAndroid()
    {
        return getPlatFormName().contains("android");
    }

    public static boolean isIos()
    {
        return getPlatFormName().contains("ios");
    }

    public static boolean isWeb()
    {
        return getPlatFormName().contains("web");
    }
}
