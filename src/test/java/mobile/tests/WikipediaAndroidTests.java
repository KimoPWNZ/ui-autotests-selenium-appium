package mobile.tests;

import mobile.screens.WikipediaArticleScreen;
import mobile.screens.WikipediaHomeScreen;
import mobile.screens.WikipediaOnboarding;
import mobile.screens.WikipediaSearchScreen;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WikipediaAndroidTests extends BaseMobileTest {

    @BeforeMethod
    public void bypassOnboardingIfAny() {
        new WikipediaOnboarding(driver).skipIfPresent();
    }

    @Test(description = "Проверка, что на главном экране отображается поиск")
    public void homeScreenHasSearch() {
        WikipediaHomeScreen home = new WikipediaHomeScreen(driver);
        Assert.assertTrue(home.isSearchVisible(), "На главном экране должен быть доступен поиск");
    }

    @Test(description = "Поиск статьи -> открыть первый результат -> проверить заголовок и контент")
    public void searchAndOpenArticleChecksTitle() {
        WikipediaHomeScreen home = new WikipediaHomeScreen(driver);
        WikipediaSearchScreen search = home.openSearch();

        search.typeQuery("Selenium");
        Assert.assertTrue(search.hasAnyResult(), "Должны появиться результаты поиска");

        WikipediaArticleScreen article = search.openFirstResult();
        Assert.assertTrue(article.contentVisible(), "Контент статьи должен отображаться");
        Assert.assertFalse(article.getTitle().isBlank(), "Заголовок статьи не должен быть пустым");
    }

    @Test(description = "Поиск статьи и проверка, что заголовок содержит ключевое слово")
    public void searchTitleContainsKeyword() {
        WikipediaHomeScreen home = new WikipediaHomeScreen(driver);
        WikipediaSearchScreen search = home.openSearch();

        String query = "Java";
        search.typeQuery(query);

        WikipediaArticleScreen article = search.openFirstResult();
        Assert.assertTrue(article.getTitle().toLowerCase().contains(query.toLowerCase()),
                "Заголовок должен содержать ключевое слово: " + query);
    }
}