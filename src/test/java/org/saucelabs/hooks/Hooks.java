package org.saucelabs.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.saucelabs.utilty.driverUtils.DriverFactory;
import org.saucelabs.utilty.driverUtils.DriverManager;
import org.saucelabs.utilty.driverUtils.PlatformUtils;

public class Hooks
{
    @Before
    public void setUp()
    {
        if (PlatformUtils.isAndroid() || PlatformUtils.isIos())
        {
            DriverManager.setMobileDriver(DriverFactory.createMobileDriver());
        } else
        {
            DriverManager.setWebDriver(DriverFactory.createWebDriver());
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
            }
        } catch (Exception e)
        {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }
}
