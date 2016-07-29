package pageObjects.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Smoke extends Messages {

    @Test
    public void test_wui_sends_sms_message() {
        //delete_all_messages();
        Messages message_page = PageFactory.initElements(driver, Messages.class);
        message_page.launch_messages_app();
        message_page.delete_all_messages();
        Browser web_page = PageFactory.initElements(driver, Browser.class);
        web_page.open_wui_page();
        web_page.enter_credentials();
        //Messages message_page = PageFactory.initElements(driver, Messages.class);
        message_page.launch_messages_app();
        String code = message_page.retrieve_code_from_message();
        web_page.enter_pin_and_submit(code);
        web_page.create_and_send_sms_message();
        web_page.is_message_presents_in_wui_conversation();
    }

    @Test
    public void test_wui_receives_sms_message() {
        Messages message_page = PageFactory.initElements(driver, Messages.class);
        message_page.launch_messages_app();
        message_page.delete_all_messages();
        message_page.create_and_send_new_message();
        Browser web_page = PageFactory.initElements(driver, Browser.class);
        web_page.open_wui_page();
        web_page.enter_credentials();
        message_page.launch_messages_app();
        String code = message_page.retrieve_code_from_message();
        web_page.enter_pin_and_submit(code);
        web_page.is_message_presents_in_wui_conversation();
    }

    @Test
    public void test_wui_receives_picture_in_mms_message() {
        Messages message_page = PageFactory.initElements(driver, Messages.class);
        message_page.launch_messages_app();
        message_page.delete_all_messages();
        Browser web_page = PageFactory.initElements(driver, Browser.class);
        web_page.open_wui_page();
        web_page.enter_credentials();
        message_page.launch_messages_app();
        String code = message_page.retrieve_code_from_message();
        web_page.enter_pin_and_submit(code);
        web_page.verify_jpg_image_in_wui_conversation();
    }

    @Test
    public void test_hui_sends_sms_message() {
        Messages message_page = PageFactory.initElements(driver, Messages.class);
        message_page.launch_messages_app();
        message_page.delete_all_messages();
        message_page.create_and_send_new_message();
        message_page.verify_message_is_sent();
    }

    @Test
    public void test_hui_send_mms_message_picture() {
        Messages message_page = PageFactory.initElements(driver, Messages.class);
        message_page.launch_messages_app();
        message_page.delete_all_messages();
        message_page.send_jpg_image();
        verify_message_with_image_is_sent();
    }

     @Test
    public void test_hui_makes_call() {
         Messages message_page = PageFactory.initElements(driver, Messages.class);
         message_page.launch_messages_app();
         message_page.delete_all_messages();
         message_page.create_and_send_new_message();
         message_page.verify_message_is_sent();
         message_page.make_a_call_from_sent_message();
         Phone phone_page = PageFactory.initElements(driver, Phone.class);
         phone_page.verify_call_is_happened();
     }

     @Test
    private void test_hui_receives_message() {
         Messages message_page = PageFactory.initElements(driver, Messages.class);
         message_page.launch_messages_app();
         message_page.delete_all_messages();
         message_page.create_and_send_message_to_himself();
         message_page.verify_that_message_is_sent_and_received_to_the_same_number();
     }

     @Test
     public void hui_receives_mms_with_an_image() {
         Messages message_page = PageFactory.initElements(driver, Messages.class);
         message_page.launch_messages_app();
         message_page.delete_all_messages();
         message_page.create_and_send_mms_with_image_to_himself();
         message_page.verify_image_is_sent_and_recived_to_the_same_number();
     }

     @Test
     public void test_sms_send_from_hui_delete_on_wui() {
         Messages message_page = PageFactory.initElements(driver, Messages.class);
         message_page.launch_messages_app();
         message_page.delete_all_messages();
         message_page.create_new_message();
         Browser web_page = PageFactory.initElements(driver, Browser.class);
         web_page.open_wui_page();
         web_page.enter_credentials();
         message_page.launch_messages_app();
         String code = message_page.retrieve_code_from_message();
         web_page.enter_pin_and_submit(code);
         web_page.is_message_presents_in_wui_conversation();
         web_page.delete_conversation_from_message_with_text();
         message_page.launch_messages_app();
         message_page.verify_conversation_message_is_deleted();
     }

     @Test
    public void test_mms_send_from_hui_delete_on_wui() {
         Messages message_page = PageFactory.initElements(driver, Messages.class);
         message_page.launch_messages_app();
         message_page.delete_all_messages();
         message_page.send_jpg_image();
         Browser web_page = PageFactory.initElements(driver, Browser.class);
         web_page.open_wui_page();
         web_page.enter_credentials();
         message_page.launch_messages_app();
         String code = message_page.retrieve_code_from_message();
         web_page.enter_pin_and_submit(code);
         web_page.verify_jpg_image_in_wui_conversation();
         web_page.delete_conversation_from_message_with_image();
         message_page.launch_messages_app();
         message_page.verify_mms_message_is_deleted();
     }

     @Test
    public void test_make_favorite_on_wui_verify_on_hui() {
         Messages message_page = PageFactory.initElements(driver, Messages.class);
         message_page.launch_messages_app();
         message_page.delete_all_messages();
         message_page.create_new_message();
         Browser web_page = PageFactory.initElements(driver, Browser.class);
         web_page.open_wui_page();
         web_page.enter_credentials();
         message_page.launch_messages_app();
         String code = message_page.retrieve_code_from_message();
         web_page.enter_pin_and_submit(code);
         web_page.is_test_message_is_present_in_wui_conversation();
         web_page.mark_conversation_as_favorite();
         message_page.launch_messages_app();
         message_page.verify_message_is_in_favorites();
     }

}