package org.saucelabs.runners;

import io.appium.java_client.AppiumDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.saucelabs.utilty.driverUtils.DriverFactory;
import org.saucelabs.utilty.driverUtils.DriverManager;
import org.saucelabs.utilty.driverUtils.PlatformUtils;
import org.testng.annotations.*;
import org.saucelabs.utilty.report.AllureReportCleaner;

@CucumberOptions(
        features = "src/test/java/org/saucelabs/features/mobile",
        glue = {
                "org/saucelabs/mobile/stepdefinitions",
                "org.saucelabs.hooks"
        },
        tags = "@mobile",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class MobileTestRunner extends AbstractTestNGCucumberTests
{

    private AppiumDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void cleanAllureReportsBeforeExecution()
    {
        AllureReportCleaner.cleanAllureReport();
    }

    @BeforeClass(alwaysRun = true)
    public void setup()
    {
        // Only create mobile driver
        driver = DriverFactory.createMobileDriver();
        DriverManager.setMobileDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void resetDriver()
    {
        if (driver != null)
        {
            driver.resetInputState();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown()
    {
        DriverManager.removeMobileDriver();
    }
}
