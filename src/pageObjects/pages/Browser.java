package pageObjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser extends Base_browser {

    String wui_url = "http://messages.att.net";
    String user_id_field = "userid";
    String user_id = "";
    String user_password_field = "password";
    String user_password = "";
    String enter_credentials_submit_button = "submitButton";
    String confirm_wireless_number_page = "confirmWirelessNumber";
    String pin_text_field = "pinVerifyInput";
    String enter_pin_next_button = "nextBtn";
    String new_message_button = "//button[@title='New Message']";
    String contact_field = "//div[@class='flex-0 crystallizedRecipients ng-scope placeholder']";
    String message_text_field = "//div[@class='composer-area nonbounce']";
    String text = "This is a test message!";
    String send_message_button = "//button[@title='Send Message Enabled']";
    String test_message_locator = "//span[contains(text(), 'Some text')]";
    String avatar_icons = "//div[@class='icons avatar-group']";
    String jpg_image_in_conversation = "//img[@data-filename='jpg.jpg']";

    @BeforeMethod
    public void setUp() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void open_wui_page() {
        open_page(wui_url);
        is_element_present("id", user_id_field);
    }

    public void enter_credentials() {
        fill_out_text_field("id", user_id_field, user_id);
        fill_out_text_field("id", user_password_field, user_password);
        click_on_element("id", enter_credentials_submit_button);
        wait_for_element("id", confirm_wireless_number_page);

    }

    public void enter_pin_and_submit(String code) {
        find_element("id", pin_text_field).sendKeys(code);
        click_on_element("id", enter_pin_next_button);
    }

    public void create_and_send_sms_message() {
        click_on_element("xpath", new_message_button);
        click_on_element("xpath", contact_field);
        find_element("xpath", contact_field).sendKeys(user_id);
        click_on_element("xpath", message_text_field);
        find_element("xpath", message_text_field).sendKeys(text);
        click_on_element("xpath", send_message_button);
    }

    public void is_message_presents_in_wui_conversation() {
        find_element("xpath", test_message_locator);
    }

    public void verify_jpg_image_in_wui_conversation() {
        List<WebElement> el = find_elements("xpath", avatar_icons);
        el.get(1).click();
        is_element_present("xpath", jpg_image_in_conversation);
    }


}
