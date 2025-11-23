package org.saucelabs.utilty.driverUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.net.MalformedURLException;

public final class DriverFactory
{

    private static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723/";

    private DriverFactory()
    { /* utility class */ }

    /**
     * Create a mobile Appium driver (Android or iOS) based on PlatformUtils.
     * Returns AppiumDriver so callers can use mobile-specific APIs.
     */
    public static AppiumDriver createMobileDriver()
    {
        String platform = PlatformUtils.getPlatFormName().toLowerCase();

        switch (platform)
        {
            case "android":
                return createAndroidDriver();
            case "ios":
                return createIosDriver();
            default:
                throw new RuntimeException("Unsupported mobile platform: " + platform);
        }
    }

    /**
     * Create a desktop/web driver (Chrome/Safari) - separate method for clarity.
     */
    public static WebDriver createWebDriver()
    {
        String browser = ConfigReader.get("browser").toLowerCase();
        switch (browser)
        {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--headless=new");
                return new ChromeDriver(chromeOptions);

            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                return new SafariDriver(safariOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--width=1400", "--height=900");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                return new EdgeDriver(edgeOptions);
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }


    private static AppiumDriver createAndroidDriver()
    {
        try
        {
            JSONObject jsonCaps = CapabilityReader.loadCapabilities("android");
            DesiredCapabilities caps = new DesiredCapabilities(jsonCaps.toMap());
            URL url = new URL(APPIUM_SERVER_URL);
            return new AndroidDriver(url, caps);
        } catch (MalformedURLException e)
        {
            throw new RuntimeException("Invalid Appium server URL: " + e.getMessage(), e);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create Android driver: " + e.getMessage(), e);
        }
    }

    private static AppiumDriver createIosDriver()
    {
        try
        {
            JSONObject jsonCaps = CapabilityReader.loadCapabilities("ios");
            DesiredCapabilities caps = new DesiredCapabilities(jsonCaps.toMap());
            URL url = new URL(APPIUM_SERVER_URL);
            return new IOSDriver(url, caps);
        } catch (MalformedURLException e)
        {
            throw new RuntimeException("Invalid Appium server URL: " + e.getMessage(), e);
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to create iOS driver: " + e.getMessage(), e);
        }
    }
}
