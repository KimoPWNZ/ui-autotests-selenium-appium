package utils.mobile;

import config.Config;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public final class MobileWaiters {
    private MobileWaiters() {}

    private static WebDriverWait wait(AndroidDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("timeouts.seconds")));
    }

    public static WebElement visible(AndroidDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(AndroidDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }
}