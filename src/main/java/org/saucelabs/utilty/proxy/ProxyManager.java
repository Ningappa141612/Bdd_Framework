package org.saucelabs.utilty.proxy;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;

public class ProxyManager
{
    private static BrowserMobProxy proxy;

    public static void startProxy()
    {
        if (proxy == null)
        {
            proxy = new BrowserMobProxyServer();
            proxy.start(0);
            proxy.newHar("network_capture");
        }
    }

    public static BrowserMobProxy getProxy()
    {
        return proxy;
    }

    public static void stopProxy()
    {
        if (proxy != null)
        {
            proxy.stop();
            proxy = null;
        }
    }
}
