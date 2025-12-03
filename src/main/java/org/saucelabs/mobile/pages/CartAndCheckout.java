package org.saucelabs.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.sl.In;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.saucelabs.utilty.CommonMobileUtility;

import java.util.Random;

public class CartAndCheckout extends CommonMobileUtility
{

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-ADD TO CART']")
    private WebElement addToCartButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/child::android.widget.TextView[1]")
    private WebElement cartCounter;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']")
    private WebElement cartIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='DESCRIPTION']")
    private WebElement descriptions;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='YOUR CART']")
    private WebElement yourCartTitle;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CHECKOUT']")
    private WebElement checkoutButton;
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-First Name']")
    private WebElement firstNameField;
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Last Name']")
    private WebElement lastnameField;
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Zip/Postal Code']")
    private WebElement postCodeField;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    private WebElement continueButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHECKOUT: OVERVIEW']")
    private WebElement checkoutReviewScreen;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-FINISH']")
    private WebElement finishButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']")
    private WebElement successMessage;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-BACK HOME']")
    private WebElement backToHomeButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Name is required']")
    private WebElement errorMessage;


    public CartAndCheckout(AppiumDriver appiumDriver)
    {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public void addProductTheCart()
    {
        swipeUp();
        click(addToCartButton);
    }

    public boolean verifyTheProductIsAddedToCart()
    {
        waitForWebElementToDisplayed(cartCounter, 3);
        return Integer.parseInt(getText(cartCounter)) >= 1;
    }

    public void tapsOnCartIcon()
    {
        click(cartIcon);
    }

    public boolean isUserRedirectedToCheckoutScreen()
    {
        return isElementDisplayed(descriptions) && isElementDisplayed(yourCartTitle);
    }

    public void tapsOnCheckoutButton()
    {
        click(checkoutButton);
    }

    public boolean isRedirectedToCheckoutInformation()
    {
        return isElementDisplayed(firstNameField)
                && isElementDisplayed(lastnameField)
                && isElementDisplayed(postCodeField);
    }

    public void enterTheCheckoutInformation(int postlCode)
    {
        String firstName = generateRandomName(5);
        String lastName = generateRandomName(5);
        sendKeys(firstNameField, firstName);
        sendKeys(lastnameField, lastName);
        sendKeys(postCodeField, String.valueOf(postlCode));
    }

    public void tapOnContinueButton()
    {
        click(continueButton);
    }

    public boolean isUserRedirectsToTheReviewScreen()
    {
        return isElementDisplayed(checkoutReviewScreen);
    }

    public void tapsOnFinishButton()
    {
        swipeUp();
        click(finishButton);
    }

    public boolean isOrderPlaceSuccessFully()
    {
        return isElementDisplayed(successMessage) && isElementDisplayed(backToHomeButton);
    }
    public boolean isErrorMessageDisplayed()
    {
        return isElementDisplayed(errorMessage);
    }
}
