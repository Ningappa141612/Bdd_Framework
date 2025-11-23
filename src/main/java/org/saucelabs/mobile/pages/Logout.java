package org.saucelabs.mobile.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.saucelabs.utilty.CommonMobileUtility;

public class Logout extends CommonMobileUtility
{
    public Logout()
    {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }
}
