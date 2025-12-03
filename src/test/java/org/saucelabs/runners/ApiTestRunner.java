package org.saucelabs.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.saucelabs.utilty.report.AllureReportCleaner;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/java/org/saucelabs/features/api",
        glue = {
                "org/saucelabs/api/stepdefinitions",
                "org.saucelabs.hooks"
        },
        tags = "@api",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class ApiTestRunner extends AbstractTestNGCucumberTests
{

    @BeforeSuite
    public void cleanAllureReportsBeforeExecution()
    {
        AllureReportCleaner.cleanAllureReport();
    }

    // No driver setup is needed for API tests
}
