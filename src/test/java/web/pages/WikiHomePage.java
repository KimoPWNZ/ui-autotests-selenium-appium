package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Waiters;

public class WikiHomePage {
    private final WebDriver driver;

    private final By searchInput = By.id("searchInput");
    private final By searchLanguageSelect = By.id("searchLanguage");
    private final By searchButton = By.cssSelector("button.pure-button.pure-button-primary-progressive");

    public WikiHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WikiHomePage typeSearch(String text) {
        Waiters.visible(driver, searchInput).clear();
        Waiters.visible(driver, searchInput).sendKeys(text);
        return this;
    }

    public WikiHomePage chooseLanguageCode(String code) {
        // на wikipedia.org selector содержит опции с value="en", "ru" и т.д.
        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(Waiters.visible(driver, searchLanguageSelect));
        select.selectByValue(code);
        return this;
    }

    public WikiSearchResultsPage submitSearch() {
        Waiters.clickable(driver, searchButton).click();
        return new WikiSearchResultsPage(driver);
    }
}