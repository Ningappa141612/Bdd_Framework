package org.saucelabs.mobile.stepdefinitions;

import io.cucumber.java.en.*;
import org.saucelabs.mobile.pages.LoginPage;

public class LoginSteps
{

    LoginPage login = new LoginPage();

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
}
