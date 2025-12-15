package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Waiters;

public class WikiEnglishMainPage {
    private final WebDriver driver;
    private final By heading = By.id("firstHeading");

    public WikiEnglishMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String headingText() {
        return Waiters.visible(driver, heading).getText().trim();
    }
}