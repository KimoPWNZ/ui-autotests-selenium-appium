package utils;

import config.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Waiters {
    private Waiters() {}

    public static WebDriverWait wait(WebDriver driver) {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(Config.getInt("timeouts.seconds")));
    }

    public static WebElement visible(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickable(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean urlContains(WebDriver driver, String fragment) {
        return wait(driver).until(ExpectedConditions.urlContains(fragment));
    }
}