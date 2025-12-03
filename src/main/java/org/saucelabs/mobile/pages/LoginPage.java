package org.saucelabs.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.saucelabs.utilty.CommonMobileUtility;
import org.testng.Assert;

public class LoginPage extends CommonMobileUtility
{

    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView[1]")
    private WebElement swagLabsLogo;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
    private WebElement userNameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
    private WebElement passwordTextField;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
    private WebElement loginButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Username and')]")
    private WebElement errorMessage;
    private String errorMessageText = "Username and password do not match any user in this service.";

    public LoginPage(AppiumDriver appiumDriver)
    {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }


    public void isSwagLogoToBeDisplayed()
    {
        isElementDisplayed(swagLabsLogo);
    }

    public void enterCredentials(String userName, String password)
    {
        sendKeys(userNameField, userName);
        sendKeys(passwordTextField, password);
    }

    public void tapLogin()
    {
        click(loginButton);
    }

    public void isErrorMessageDisplayed()
    {
        waitForWebElementToDisplayed(errorMessage, 5);
        Assert.assertTrue(getText(errorMessage).equalsIgnoreCase(errorMessageText));
    }
}
