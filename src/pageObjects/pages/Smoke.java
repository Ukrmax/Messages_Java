package pageObjects.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.After;
import org.junit.Before;

import java.net.URL;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageObjects.pages.Browser;

public class Smoke extends Messages {

    @Test
    public void test_wui_sends_sms_message() {
        //delete_all_messages();
        Messages page = PageFactory.initElements(driver, Messages.class);
        page.launch_messages_app();
        page.delete_all_messages();
        Browser web_page = PageFactory.initElements(driver, Browser.class);
        web_page.open_wui_page();
        web_page.enter_credentials();
        //Messages page = PageFactory.initElements(driver, Messages.class);
        page.launch_messages_app();
        String code = page.retrieve_code_from_message();
        web_page.enter_pin_and_submit(code);
        web_page.create_and_send_sms_message();
        web_page.is_message_presents_in_wui_conversation();
    }

    @Test
    public void test_wui_receives_sms_message() {
        Messages page = PageFactory.initElements(driver, Messages.class);
        page.launch_messages_app();
        page.delete_all_messages();
        page.create_and_send_new_message();
        Browser web_page = PageFactory.initElements(driver, Browser.class);
        web_page.open_wui_page();
        web_page.enter_credentials();
        page.launch_messages_app();
        String code = page.retrieve_code_from_message();
        web_page.enter_pin_and_submit(code);
        web_page.is_message_presents_in_wui_conversation();
    }

    @Test
    public void test_wui_receives_picture_in_mms_message() {
        Messages page = PageFactory.initElements(driver, Messages.class);
        page.launch_messages_app();
        page.delete_all_messages();
        Browser web_page = PageFactory.initElements(driver, Browser.class);
        web_page.open_wui_page();
        web_page.enter_credentials();
        page.launch_messages_app();
        String code = page.retrieve_code_from_message();
        web_page.enter_pin_and_submit(code);
        web_page.verify_jpg_image_in_wui_conversation();
    }

    @Test
    public void test_hui_sends_sms_message() {

    }


}