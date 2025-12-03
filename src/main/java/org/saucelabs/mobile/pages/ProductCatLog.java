package org.saucelabs.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.saucelabs.utilty.CommonMobileUtility;

import java.util.List;

public class ProductCatLog extends CommonMobileUtility
{
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    private WebElement productTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Sauce Labs')]")
    private List<WebElement> sauceLabsProducts;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='BACK TO PRODUCTS']")
    private WebElement backToProductButton;


    public ProductCatLog(AppiumDriver appiumDriver)
    {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public boolean isProductTitleDisplayed()
    {
        return productTitle.isDisplayed();
    }

    public boolean isMoreThanOneProductsDisplayed()
    {
        waitForWebElementToDisplayed(sauceLabsProducts.get(0), 2);
        return getElementSize(sauceLabsProducts) >= 1;
    }

    public void selectTheFirstProductFromList()
    {
        click(sauceLabsProducts.get(0));
    }

    public boolean isUserRedirectedToDetailsPage()
    {
        waitForWebElementToDisplayed(backToProductButton, 10);
        return isElementDisplayed(sauceLabsProducts.get(0)) || isElementDisplayed(backToProductButton);
    }
}
