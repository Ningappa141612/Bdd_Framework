package org.saucelabs.mobile.stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.saucelabs.mobile.pages.CartAndCheckout;
import org.testng.Assert;

public class CartAndCheckoutSteps
{
    CartAndCheckout cartAndCheckout = new CartAndCheckout();

    @When("the user adds the product to the cart")
    public void theUserAddsTheProductToTheCart()
    {
        cartAndCheckout.addProductTheCart();
    }

    @Then("the product should be added to the cart")
    public void theProductShouldBeAddedToTheCart()
    {
        Assert.assertTrue(cartAndCheckout.verifyTheProductIsAddedToCart(),
                "Failed to add the product to the cart");
    }

    @When("the user taps on the cart icon")
    public void theUserTapsOnTheCartIcon()
    {
        cartAndCheckout.tapsOnCartIcon();
    }

    @Then("the user should be redirected to the cart page")
    public void theUserShouldRedirectToTheCartPage()
    {
        Assert.assertTrue(cartAndCheckout.isUserRedirectedToCheckoutScreen(),
                "Failed to redirect to the checkout screen");
    }

    @When("the user taps on the checkout button")
    public void userTapsOnTheCheckoutButton()
    {
        cartAndCheckout.tapsOnCheckoutButton();
    }

    @Then("the user should be redirected to the checkout information screen")
    public void userShouldRedirectsToTheCheckoutInformationScreen()
    {
        Assert.assertTrue(cartAndCheckout.isRedirectedToCheckoutInformation(),
                "Failed to redirect to the checkout information screen");
    }

    @When("the user enters the checkout information {int}")
    public void userEnterTheCheckoutInformation(int postlCode)
    {
        cartAndCheckout.enterTheCheckoutInformation(postlCode);
    }

    @And("the user taps on the continue button")
    public void userTapsOnTheContinueButton()
    {
        cartAndCheckout.tapOnContinueButton();
    }

    @Then("the user should be redirected to the checkout overview screen")
    public void verifyUserRedirectsToTheCheckoutOverViewScreen()
    {
        Assert.assertTrue(cartAndCheckout.isUserRedirectsToTheReviewScreen(),
                "Failed to redirect to the checkout review screen");
    }

    @When("the user taps on the finish button")
    public void userTapsOnFinishButton()
    {
        cartAndCheckout.tapsOnFinishButton();
    }

    @Then("the order should be completed successfully")
    public void theOrderShouldBeCompletedSuccessfully()
    {
        Assert.assertTrue(cartAndCheckout.isOrderPlaceSuccessFully(),
                "Failed to place an order");
    }

    @Then("validate the error messages for empty fields")
    public void validateTheErrorMessagesForEmptyFields()
    {
        cartAndCheckout.isErrorMessageDisplayed();
    }
}
