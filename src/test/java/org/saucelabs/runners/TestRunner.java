package org.saucelabs.runners;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.saucelabs.utilty.driverUtils.DriverManager;
import org.saucelabs.utilty.driverUtils.PlatformUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.saucelabs.utilty.report.AllureReportCleaner;

@CucumberOptions(
        features = "src/test/java/org/saucelabs/features",
        glue = {"org.saucelabs.mobile.stepdefinitions", "org.saucelabs.hooks"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests
{

    @BeforeSuite
    public void cleanAllureReportsBeforeExecution()
    {
        AllureReportCleaner.cleanAllureReport();
    }

    @AfterMethod
    public void killAndRelaunch()
    {
        AppiumDriver driver = null;

        if (PlatformUtils.isAndroid())
        {
            driver = DriverManager.getMobileDriver();
        } else if (PlatformUtils.isIos())
        {
            driver = DriverManager.getMobileDriver();
        }
        if (driver != null)
        {
            driver.resetInputState();
        }
    }

    @AfterClass
    public void tearDown()
    {

        if (PlatformUtils.isAndroid() || PlatformUtils.isIos())
        {
            DriverManager.removeMobileDriver();
        } else
        {
            DriverManager.removeWebDriver();
        }
    }
}

