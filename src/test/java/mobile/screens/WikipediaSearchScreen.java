package mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.mobile.MobileWaiters;

public class WikipediaSearchScreen {
    private final AndroidDriver driver;

    private final By searchInput = By.id("org.wikipedia:id/search_src_text");
    private final By firstResult = By.id("org.wikipedia:id/page_list_item_title");

    public WikipediaSearchScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public WikipediaSearchScreen typeQuery(String query) {
        MobileWaiters.visible(driver, searchInput).sendKeys(query);
        return this;
    }

    public boolean hasAnyResult() {
        return MobileWaiters.visible(driver, firstResult).isDisplayed();
    }

    public WikipediaArticleScreen openFirstResult() {
        MobileWaiters.clickable(driver, firstResult).click();
        return new WikipediaArticleScreen(driver);
    }
}