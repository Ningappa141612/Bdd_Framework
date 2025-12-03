package org.saucelabs.utilty;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.saucelabs.utilty.driverUtils.DriverManager;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommonMobileUtility
{

    protected AppiumDriver appiumDriver;
    protected IOSDriver iosDriver;
    protected AndroidDriver androidDriver;

    public CommonMobileUtility()
    {
        // Do NOT initialize driver here
    }

    public void initDriver()
    {
        this.appiumDriver = DriverManager.getMobileDriver();
        if (appiumDriver == null)
        {
            throw new RuntimeException("MobileDriver is not initialized! Call DriverManager.setMobileDriver() before using utilities.");
        }

        String platform = appiumDriver.getCapabilities()
                .getPlatformName().toString().toLowerCase();

        if (platform.contains("ios") && appiumDriver instanceof IOSDriver)
        {
            iosDriver = (IOSDriver) appiumDriver;
        } else if (platform.contains("android") && appiumDriver instanceof AndroidDriver)
        {
            androidDriver = (AndroidDriver) appiumDriver;
        }
    }


    public WebElement waitForWebElementToDisplayed(WebElement element, int wait)
    {
        try
        {
            return new WebDriverWait(appiumDriver, Duration.ofSeconds(wait))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementDisplayed(WebElement element)
    {
        try
        {
            return element != null && element.isDisplayed();
        } catch (Exception e)
        {
            return false;
        }
    }

    public WebElement waitForClickable(WebElement element)
    {
        return new WebDriverWait(appiumDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element)
    {
        waitForClickable(element).click();
    }


    public void sendKeys(WebElement element, String text)
    {
        element = waitForWebElementToDisplayed(element, 10);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element)
    {
        return waitForWebElementToDisplayed(element, 10).getText();
    }

    public int getElementSize(List<WebElement> elements)
    {
        return elements.size();
    }

    public void swipeUp()
    {
        Dimension size = appiumDriver.manage().window().getSize();
        int width = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        /**
         * new TouchAction(androidDriver)
         *.press(PointOption.point(width, startY))
         * .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
         * .moveTo(PointOption.point(width, endY)) .perform().release();
         */
        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), width, startY));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), width, endY));

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        appiumDriver.perform(Arrays.asList(swipe));
    }

    public void hideKeyboard()
    {
        try
        {
            androidDriver.hideKeyboard();
        } catch (Exception ignored)
        {
        }
    }

    public static String generateRandomName(int length)
    {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // generate random lowercase a–z letters
        for (int i = 0; i < length; i++)
        {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }

        // Capitalize first letter
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }


}
