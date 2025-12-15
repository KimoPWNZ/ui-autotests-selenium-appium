package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Waiters;

public class WikiArticlePage {
    private final WebDriver driver;

    private final By heading = By.id("firstHeading");
    private final By content = By.id("mw-content-text");
    private final By mainPageLink = By.id("n-mainpage-description"); // sidebar: Main page (en); на ru может отличаться

    public WikiArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public String headingText() {
        return Waiters.visible(driver, heading).getText().trim();
    }

    public boolean contentVisible() {
        return Waiters.visible(driver, content).isDisplayed();
    }

    public void openMainPageFromSidebar() {
        Waiters.clickable(driver, mainPageLink).click();
    }
}