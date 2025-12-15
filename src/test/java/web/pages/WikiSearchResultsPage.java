package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Waiters;

public class WikiSearchResultsPage {
    private final WebDriver driver;

    private final By firstResultHeadingLink = By.cssSelector("ul.mw-search-results li.mw-search-result a");

    public WikiSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean hasResults() {
        return Waiters.visible(driver, firstResultHeadingLink).isDisplayed();
    }

    public WikiArticlePage openFirstResult() {
        Waiters.clickable(driver, firstResultHeadingLink).click();
        return new WikiArticlePage(driver);
    }
}