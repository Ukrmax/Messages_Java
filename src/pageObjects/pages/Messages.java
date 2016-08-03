package pageObjects.pages;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Messages extends Base {

    AndroidDriver driver;

    static String phone_number = "";
    static String user_id = "";
    String test_message_text = "This is a test message";

    String star_icon = "//android.widget.ImageView[@content-desc='Favorite']";
    String favorites_button = "com.att.android.mobile.attmessages:id/filterbarSavedButton";
    String empty_messages_section = "android:id/empty";
    String any_avatar = "com.att.android.mobile.attmessages:id/avatar";
    String message_text_balloon = "com.att.android.mobile.attmessages:id/balloonText";
    String delete_message_from_conversation = "//android.widget.TextView[@test_message_text='Delete Message']";
    String delete_All_confirmation_button = "com.att.android.mobile.attmessages:id/primaryButton";
    String all_button = "android:id/selectAll";
    String menu_button = "//android.widget.ImageButton[@content-desc='Menu']";
    String delete_menu_button = "//android.widget.TextView[@test_message_text='Delete']";
    String delete_All_button = "com.att.android.mobile.attmessages:id/ListEditClearAllBt";

    String messages_app_package_name = "com.att.android.mobile.attmessages";
    String messages_app_activity_name = "com.att.ui.screen.ConversationListScreen";

    String message_with_code = "//android.widget.TextView[@test_message_text='(746)118-88']";
    String conversation_header = "com.att.android.mobile.attmessages:id/ConversationScreenUsername";
    String att_sent_code_number = "(746)118-88";
    String create_new_message_btn = "com.att.android.mobile.attmessages:id/action_new_message";

    String contact_field_in_focus = "com.att.android.mobile.attmessages:id/recipientListField";
    String contact_field_out_of_focus = "com.att.android.mobile.attmessages:id/recipientFieldOutOfFocus";

    String message_text_locator = "com.att.android.mobile.attmessages:id/ReplyEditText";
    String send_message_button = "com.att.android.mobile.attmessages:id/sendButton";

    String plus_button_message_text_field = "com.att.android.mobile.attmessages:id/cts_insertButton";
    String attach_general_button = "com.att.android.mobile.attmessages:id/cts_attach_general";
    String attach_section_from_button = "android:id/up";
    String attach_section_image_button = "//android.widget.TextView[@test_message_text='Images']";
    String image_folder = "//android.widget.TextView[@test_message_text='Image_formats']";
    String jpg_file = "//android.widget.TextView[@test_message_text='jpg.jpg']";
    String attach_section_camera_three_dot_menu = "//android.widget.ImageButton[@content-desc='More options']";
    String attach_section_camera_list_view = "//android.widget.TextView[@test_message_text='List view']";

    String one_image_in_message_text_filed = "Image attachment\n";

    String call_button = "com.att.android.mobile.attmessages:id/action_bar_call";

    String conversation_buble = "com.att.android.mobile.attmessages:id/conversationBubble";

    String message_text_on_initial_page = "com.att.android.mobile.attmessages:id/info";
    String mms_message_text_on_initial_page = "1 photo attached";

    public static String m = "";

    @BeforeMethod
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","1bdc6271");
        capabilities.setCapability("platformVersion", "4.4");
        //capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.sec.android.app.launcher");
        capabilities.setCapability("appActivity", "com.android.launcher2.Launcher");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //killing all background apps
        try {
            find_element("xpath", "//android.widget.TextView[@test_message_text='No recent apps']");
        } catch (Exception e) {
            driver.pressKeyCode(187);
            find_element("id", "com.android.systemui:id/recents_RemoveAll_button_kk").click();
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void delete_all_messages() {
        //verification if favorites messages are present if yes then deleting them
         List<WebElement> el = driver.findElements("xpath",star_icon);
        if (el.size() != 0) {
            find_element("id", favorites_button);
            try {
                find_element("id", empty_messages_section);
            } catch (NoSuchElementException e) {
                List<WebElement> el1 = driver.findElements("id", any_avatar);
                for (int i=0; i<=el1.size(); i++) {
                    el1.get(0).click();
                    try {
                        while (true) {
                            long_tap_on_message_text_balloon();
                            tap_on_element("xpath", delete_message_from_conversation);
                            tap_on_element("xpath", delete_All_confirmation_button);
                        }
                    } catch (Exception e1) {
                    }
                }
            }
        }
        //deleting regular messages
        tap_on_element("id", all_button);
        try {
           find_element("id", empty_messages_section);
        } catch (NoSuchElementException e) {
            tap_on_element("xpath", menu_button);
            tap_on_element("xpath", delete_menu_button);
            tap_on_element("id", delete_All_button);
            tap_on_element("id", delete_All_confirmation_button);
        }

    }

    public void long_tap_on_message_text_balloon() {
        long_tap("id", message_text_balloon);
    }

    public void launch_messages_app () {
        launch_app(messages_app_package_name, messages_app_activity_name);
    }

    public String retrieve_code_from_message() {
        tap_on_element("xpath", message_with_code);

        if (conversation_has_loaded() == true) {

        } else {
            //add two seconds delay!!!
            tap_on_element("xpath", message_with_code);
        }
        String m_text = find_element("xpath", message_text_balloon).getText();
        List<String> li = Arrays.asList(m_text.split(":"));
        String code = li.get(-1);
        return code;
    }

    public boolean conversation_has_loaded() {
        try {
            String element;
            element = find_element("xpath", conversation_header).getText();
            Objects.equals(element, att_sent_code_number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void create_and_send_new_message() {
        create_new_message();
        type_phone_number(phone_number);
        fill_message_text_field();
        tap_on_the_send_message_button();
    }

    public void create_and_send_message_to_himself() {
        create_new_message();
        type_phone_number(user_id);
        fill_message_text_field();
        tap_on_the_send_message_button();
    }

    public void create_and_send_mms_with_image_to_himself() {
        create_new_message();
        type_phone_number(user_id);
        attach_jpg_image_to_message();
    }

    public void create_new_message() {
        tap_on_element("id", create_new_message_btn);
    }

    //Doesn't work normal way in python, check Java !!!!!!!
    public void type_phone_number(String phone_number) {
        try {
            find_element("id", contact_field_in_focus);
        } catch (Exception e) {
            tap_on_element("id", contact_field_out_of_focus);
        }
        List<String> code_list = new ArrayList<>();
        Dictionary keycode = new Hashtable();
        keycode.put(0, 7);
        keycode.put(1, 8);
        keycode.put(2, 9);
        keycode.put(3, 10);
        keycode.put(4, 11);
        keycode.put(5, 12);
        keycode.put(6, 13);
        keycode.put(7, 14);
        keycode.put(8, 15);
        keycode.put(9, 16);

        List<String> li = Arrays.asList(phone_number.split("(?!^)"));

        int count = 0;
        while (count < li.size()) {
            int el_num = Integer.parseInt(li.get(count));
            code_list.add(count, keycode.get(el_num).toString());
            count++;
        }

        int i = 0;
        while (i < code_list.size()) {
            send_key_code(Integer.parseInt(code_list.get(i)));
            i ++;
        }
    }

    public void fill_message_text_field() {
        fill_out_field("id", message_text_locator, test_message_text);
        assert_text("id", message_text_locator, test_message_text);
    }

    public void send_jpg_image() {
        create_new_message();
        type_phone_number(phone_number);
        attach_jpg_image_to_message();
    }

    public void attach_jpg_image_to_message() {
        tap_plus_button_text_message_field();
        tap_on_element("id", attach_general_button);
        tap_on_element("id", attach_section_from_button);
        tap_on_element("xpath", attach_section_image_button);
        tap_on_element("xpath", image_folder);

        try {
           tap_on_element("xpath", jpg_file);
        } catch (Exception e) {
           tap_on_element("xpath", attach_section_camera_three_dot_menu);
            tap_on_element("xpath", attach_section_camera_list_view);
            tap_on_element("xpath", jpg_file);
        }
            tap_on_the_send_message_button();
    }

    public void tap_plus_button_text_message_field() {
        tap_on_element("id", plus_button_message_text_field);
    }

    public void tap_on_the_send_message_button() {
        tap_on_element("id", send_message_button);
    }

    public void verify_message_is_sent() {
        String el = find_element("id", message_text_balloon).getText();
        Assert.assertEquals(el, test_message_text);
    }

    public void verify_message_with_image_is_sent() {
           String el = find_element("id", message_text_balloon).getText();
            Assert.assertEquals(el, one_image_in_message_text_filed);
    }

    public void make_a_call_from_sent_message() {
           tap_on_element("id", call_button);
    }

    public void verify_that_message_is_sent_and_received_to_the_same_number() {
        try {
            List<WebElement> bubbles = find_elements("id", conversation_buble);
            assert bubbles.size() == 2;
        }  catch (Exception e) {
            System.out.println("No sent message in the Conversation!");
        }
        String el = find_element("id", message_text_balloon).getText();
        Assert.assertEquals(el, test_message_text);
    }

    public void verify_image_is_sent_and_recived_to_the_same_number() {
        try {
            List<WebElement> bubbles = find_elements("id", conversation_buble);
            assert bubbles.size() == 2;
        }  catch (Exception e) {
            System.out.println("No sent message in the Conversation!");
        }
        String el = find_element("id", conversation_buble).getAttribute("name");
        Assert.assertEquals(el, one_image_in_message_text_filed);
    }

    public void assert_message_is_not_present() {
        String el = find_element("id", message_text_on_initial_page).getText();
        Assert.assertNotEquals(el, test_message_text);
    }

    public void assert_mms_message_is_not_present() {
        String el = find_element("id", mms_message_text_on_initial_page).getText();
        Assert.assertNotEquals(el, test_message_text);
    }

    public void verify_conversation_message_is_deleted() {
        try {
            assert_message_is_not_present();
        } catch (Exception e) {
            //add 10 sec time waiting
            assert_message_is_not_present();
        }
    }

    public void verify_mms_message_is_deleted() {
        try {
            assert_mms_message_is_not_present();
        } catch (Exception e) {
            //add 10 sec time waiting
            assert_mms_message_is_not_present();
        }
    }

    public void verify_message_is_in_favorites() {
        tap_on_element("id", favorites_button);
        String el = find_element("id", message_text_on_initial_page).getText();
        Assert.assertEquals(el, test_message_text);
        is_element_present("xpath", star_icon);
    }

}