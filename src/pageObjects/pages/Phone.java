package pageObjects.pages;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import pageObjects.pages.Messages.*;

/**
 * Created by maxivanov on 7/28/16.
 */
public class Phone extends Base{

    AndroidDriver driver;

    String contact_name = "com.android.incallui:id/name";
    String end_call_button = "com.android.incallui:id/endButton";

    public void verify_call_is_happened() {
        Assert.assertEquals(find_element("id", contact_name).getText(), Messages.phone_number);
        tap_on_element("id", end_call_button);
    }
}
