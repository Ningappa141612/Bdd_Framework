package org.saucelabs.mobile.stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.saucelabs.mobile.pages.LoginPage;
import org.saucelabs.mobile.pages.ProductCatLog;
import org.saucelabs.utilty.CommonMobileUtility;
import org.saucelabs.utilty.driverUtils.DriverFactory;
import org.saucelabs.utilty.driverUtils.DriverManager;
import org.testng.Assert;

public class ProductCatLogSteps
{
    private AppiumDriver driver;
    private ProductCatLog productCatLog;

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
        productCatLog = new ProductCatLog(driver);

        if (productCatLog instanceof CommonMobileUtility)
        {
            ((CommonMobileUtility) productCatLog).initDriver();
        }
    }

    @Then("the user should see the product catalog page")
    public void verifyProductPage()
    {
        Assert.assertTrue(productCatLog.isProductTitleDisplayed(),
                "Product title is not displayed");
    }

    @And("the list of products should be displayed")
    public void verifyTheListOfProductDisplayed()
    {
        Assert.assertTrue(productCatLog.isMoreThanOneProductsDisplayed(),
                "Products are not listed in the listing page");
    }

    @When("the user selects a product from the listing page")
    public void theUserSelectsAProductFromTheListingPage()
    {
        productCatLog.selectTheFirstProductFromList();
    }

    @Then("the user should be redirected to the product details screen")
    public void theUserShouldBeRedirectedToTheProductDetailsScreen()
    {
        Assert.assertTrue(productCatLog.isUserRedirectedToDetailsPage(),
                "Failed to redirect to the product details page");
    }

}
