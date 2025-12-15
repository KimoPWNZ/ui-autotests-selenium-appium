package web.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import web.pages.*;

public class WikipediaWebTests extends BaseWebTest {

    @DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][]{
                {"Selenium (software)", "en"},
                {"Test automation", "en"}
        };
    }

    @Test(description = "Поиск статьи и проверка, что результаты есть + открытие первой статьи и проверка заголовка",
            dataProvider = "searchQueries")
    public void searchOpensCorrectArticle(String query, String lang) {
        WikiHomePage home = new WikiHomePage(driver);
        WikiSearchResultsPage results = home.chooseLanguageCode(lang)
                .typeSearch(query)
                .submitSearch();

        Assert.assertTrue(results.hasResults(), "Ожидались результаты поиска");

        WikiArticlePage article = results.openFirstResult();
        Assert.assertTrue(article.contentVisible(), "Контент статьи должен быть видимым");
        Assert.assertTrue(driver.getCurrentUrl().contains("/wiki/"), "URL должен вести на статью (/wiki/...)");
        Assert.assertFalse(article.headingText().isEmpty(), "Заголовок статьи не должен быть пустым");
    }

    @Test(description = "Навигация: открыть статью и перейти на Main page через меню слева (en wiki)")
    public void navigationToMainPageWorks() {
        WikiHomePage home = new WikiHomePage(driver);
        WikiArticlePage article = home.chooseLanguageCode("en")
                .typeSearch("Java")
                .submitSearch()
                .openFirstResult();

        article.openMainPageFromSidebar();

        WikiEnglishMainPage mainPage = new WikiEnglishMainPage(driver);
        Assert.assertEquals(mainPage.headingText(), "Main Page", "Должна открыться Main Page");
    }

    @Test(description = "Смена языка на EN через выбор языка на главной wikipedia.org и проверка домена en.wikipedia.org")
    public void languageSwitchToEnglishChangesDomain() {
        WikiHomePage home = new WikiHomePage(driver);
        home.chooseLanguageCode("en")
                .typeSearch("Wikipedia")
                .submitSearch();

        Assert.assertTrue(driver.getCurrentUrl().contains("en.wikipedia.org"),
                "Ожидался домен en.wikipedia.org после поиска с English");
    }

    @Test(description = "Проверка базовых элементов статьи: заголовок и контент присутствуют")
    public void articleHasHeadingAndContent() {
        WikiHomePage home = new WikiHomePage(driver);
        WikiArticlePage article = home.chooseLanguageCode("en")
                .typeSearch("Software testing")
                .submitSearch()
                .openFirstResult();

        Assert.assertTrue(article.contentVisible(), "Контент статьи должен отображаться");
        Assert.assertFalse(article.headingText().isBlank(), "Заголовок статьи должен быть непустым");
    }
}