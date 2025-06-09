package com.example.helloworld;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class HelloWorldAppTest {
    private static AndroidDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554") // Replace with your device ID
                .setAppPackage("com.example.helloworldapp")
                .setAppActivity(".MainActivity")
                .setAutomationName("UiAutomator2")
                .setNoReset(false); // Changed to false to reset app state

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHelloWorldApp() {
        // Verify initial text
        WebElement helloText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("com.example.helloworldapp:id/helloText")));
        Assertions.assertEquals("Hello World!", helloText.getText());

        // Click the button
        WebElement clickButton = driver.findElement(AppiumBy.id("com.example.helloworldapp:id/clickButton"));
        clickButton.click();

        // Verify text after click
        wait.until(ExpectedConditions.textToBePresentInElement(helloText, "Button Clicked!"));
        Assertions.assertEquals("Button Clicked!", helloText.getText());
    }
}