package mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.mobile.MobileWaiters;

public class WikipediaArticleScreen {
    private final AndroidDriver driver;

    private final By title = By.id("org.wikipedia:id/view_page_title_text");
    private final By content = By.id("org.wikipedia:id/page_contents_container");

    public WikipediaArticleScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return MobileWaiters.visible(driver, title).getText().trim();
    }

    public boolean contentVisible() {
        return MobileWaiters.visible(driver, content).isDisplayed();
    }
}