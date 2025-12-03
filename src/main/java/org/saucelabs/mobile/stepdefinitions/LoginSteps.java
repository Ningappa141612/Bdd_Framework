package org.saucelabs.mobile.stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.saucelabs.mobile.pages.LoginPage;
import org.saucelabs.utilty.CommonMobileUtility;
import org.saucelabs.utilty.driverUtils.DriverFactory;
import org.saucelabs.utilty.driverUtils.DriverManager;
import org.saucelabs.utilty.proxy.HarUtils;
import org.saucelabs.utilty.proxy.ProxyManager;

import java.util.List;

public class LoginSteps
{

    private AppiumDriver driver;
    private LoginPage login;

    @Before
    public void setUp()
    {
        // Lazy initialization
        driver = DriverManager.getMobileDriver();
        if (driver == null)
        {
            driver = DriverFactory.createMobileDriver();
            DriverManager.setMobileDriver(driver);
        }

        // Initialize page objects
        login = new LoginPage(driver);

        if (login instanceof CommonMobileUtility)
        {
            ((CommonMobileUtility) login).initDriver();
        }
    }


    @Given("the user launches the app")
    public void launchApp()
    {
        login.isSwagLogoToBeDisplayed();
    }

    @When("the user enters valid credentials {string} {string}")
    public void enterValidCredentials(String username, String password)
    {
        login.enterCredentials(username, password);
    }

    @And("the user taps on login button")
    public void tapLogin()
    {
        login.tapLogin();
    }

    @Then("validate the error message on failure")
    public void validateTheErrorMessageOnFailure()
    {
        login.isErrorMessageDisplayed();
    }

    /*@Then("Validate API call {string} returns status {int}")
    public void validateApi(String apiUrl, int statusCode)
    {
        Har har = ProxyManager.getProxy().getHar();

        List<HarEntry> entries = HarUtils.filterByUrl(har, apiUrl);

        HarEntry latest = HarUtils.getLatest(entries);

        assert latest != null : "No API call found for: " + apiUrl;
        assert latest.getResponse().getStatus() == statusCode
                : "Expected " + statusCode + " but found " + latest.getResponse().getStatus();
    }*/

}
