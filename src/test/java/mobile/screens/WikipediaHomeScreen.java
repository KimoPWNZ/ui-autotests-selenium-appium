package mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.mobile.MobileWaiters;

public class WikipediaHomeScreen {
    private final AndroidDriver driver;

    // Часто стабильный локатор в Wikipedia: поле поиска на главном экране
    private final By searchContainer = By.id("org.wikipedia:id/search_container");
    private final By searchSrcText = By.id("org.wikipedia:id/search_src_text");

    public WikipediaHomeScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isSearchVisible() {
        return MobileWaiters.visible(driver, searchContainer).isDisplayed();
    }

    public WikipediaSearchScreen openSearch() {
        MobileWaiters.clickable(driver, searchContainer).click();
        return new WikipediaSearchScreen(driver);
    }

    public void typeInSearchOnOverlay(String query) {
        MobileWaiters.visible(driver, searchSrcText).sendKeys(query);
    }
}