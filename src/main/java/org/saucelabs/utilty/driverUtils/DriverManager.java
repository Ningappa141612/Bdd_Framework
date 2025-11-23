package org.saucelabs.utilty.driverUtils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public final class DriverManager
{

    private static final ThreadLocal<AppiumDriver> mobileDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    private DriverManager()
    {
    }

    public static void setMobileDriver(AppiumDriver driver)
    {
        mobileDriver.set(driver);
    }

    public static AppiumDriver getMobileDriver()
    {
        return mobileDriver.get();
    }

    public static void removeMobileDriver()
    {
        AppiumDriver d = mobileDriver.get();
        if (d != null)
        {
            try
            {
                d.quit();
            } catch (Exception ignored)
            {
                throw new RuntimeException("There is no driver to close");
            }
        }
        mobileDriver.remove();
    }

    public static void setWebDriver(WebDriver driver)
    {
        webDriver.set(driver);
    }

    public static WebDriver getWebDriver()
    {
        return webDriver.get();
    }

    public static void removeWebDriver()
    {
        WebDriver d = webDriver.get();
        if (d != null)
        {
            try
            {
                d.quit();
            } catch (Exception ignored)
            {
                throw new RuntimeException("There is no driver to close");
            }
        }
        webDriver.remove();
    }
}

