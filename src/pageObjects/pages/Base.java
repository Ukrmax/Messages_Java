package pageObjects.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import pageObjects.pages.Messages;


public class Base {

    public static AndroidDriver driver;


    public WebElement find_element(String locatorMode,  String locator) {
        WebElement element;
        element  = null;
        if (locatorMode == "id") {
            element = driver.findElementById(locator);
        } else if (locatorMode == "xpath") {
            element = driver.findElementByXPath(locator);
        } else if (locatorMode == "css") {
            element = driver.findElementByCssSelector(locator);
            } else if (locatorMode == "name") {
                element = driver.findElementByName(locator);
        }
        return element;
    }

    public void long_tap(String locatorMode,  String locator) {
        WebElement el = find_element(locatorMode, locator);
        TouchAction longtap = new TouchAction(driver).longPress(el, 1000);
        longtap.perform();
    }

    public void tap_on_element(String locatorMode, String locator)  {
        try {
            find_element(locatorMode, locator).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            find_element(locatorMode, locator).click();
        }
    }

    public void launch_app(String packageName, String activityName) {
        driver.startActivity(packageName, activityName);
    }

    public void fill_out_field(String locatorMode, String locator, String text) {
        find_element(locatorMode, locator).clear();
        find_element(locatorMode, locator).sendKeys(text);
    }

    public void assert_text(String locatorMode, String locator, String text) {
        String el = find_element(locatorMode, locator).getText();
        Assert.assertEquals(el, text);

    }

}
