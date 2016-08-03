package pageObjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Base_browser {


    public static WebDriver driver;

    public WebElement find_element(String locatorMode, String locator) {
        WebElement element;
        element = null;
        if (locatorMode == "id") {
            element = driver.findElement(By.id(locator));
        } else if (locator == "xpath") {
            element = driver.findElement(By.xpath(locator));
        } else if (locator == "css") {
            element = driver.findElement(By.cssSelector(locator));
        } else if (locator == "name") {
            element = driver.findElement(By.name(locator));
        }
        return element;
    }

    public List<WebElement> find_elements(String locatorMode, String locator) {
        List<WebElement> elements;
        elements = null;
        if (locatorMode == "id") {
           elements = driver.findElements(By.id(locator));
        } else if (locator == "xpath") {
            elements = driver.findElements(By.xpath(locator));
        } else if (locator == "css") {
            elements = driver.findElements(By.cssSelector(locator));
        } else if (locator == "name") {
            elements = driver.findElements(By.name(locator));
        }
        return elements;
    }

    public void open_page(String Url) {
        driver.get(Url);
    }

    public void is_element_present (String locatorMode, String locator) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            find_element(locatorMode, locator);
        } catch (NoSuchElementException e) {
            System.err.println("Caught NoSuchElementException" + e.getMessage());
        }
    }

    public void fill_out_text_field(String locatorMode, String locator, String text) {
            wait_for_element(locatorMode, locator).clear();
            find_element(locatorMode, locator).sendKeys(text);
    }

    public WebElement wait_for_element(String locatorMode, String locator) {
        WebElement element;
        element = null;
        if (locatorMode == "id") {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
        } else if (locator == "xpath") {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        } else if (locator == "css") {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
        } else if (locator == "name") {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
        }
        return element;
    }

    public void click_on_element(String locatorMode, String locator) {
        find_element(locatorMode, locator).click();
    }

    public void send_text_using_action_chains(String text) {

    }

    public void hover_over_element(WebElement element) {
        Actions hover = new Actions(driver);
        hover.moveToElement(element).perform();
    }

}
