package org.saucelabs.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.saucelabs.utilty.driverUtils.ConfigReader;
import org.saucelabs.utilty.driverUtils.DriverFactory;
import org.saucelabs.utilty.driverUtils.DriverManager;
import org.saucelabs.utilty.driverUtils.PlatformUtils;
import org.saucelabs.utilty.proxy.ProxyManager;

import java.io.File;

public class Hooks
{
    @Before
    public void setUp()
    {
        if (ConfigReader.get("isProxyRequired").equalsIgnoreCase("true"))
        {
            ProxyManager.startProxy();
        }
    }

    @After
    public void tearDown(Scenario scenario)
    {
        try
        {
            // Screenshot only on failure
            if (scenario.isFailed())
            {
                WebDriver driver;

                if (PlatformUtils.isAndroid() || PlatformUtils.isIos())
                {
                    driver = DriverManager.getMobileDriver();
                } else
                {
                    driver = DriverManager.getWebDriver();
                }

                if (driver != null)
                {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Failure Screenshot");
                }
                if (ConfigReader.get("isProxyRequired").equalsIgnoreCase("true"))
                {
                    Har har = ProxyManager.getProxy().getHar();
                    har.writeTo(new File("target/network-" + scenario.getName() + ".har"));
                    ProxyManager.stopProxy();
                }
            }
        } catch (Exception e)
        {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }
}
