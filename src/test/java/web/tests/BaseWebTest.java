package web.tests;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public abstract class BaseWebTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        String browser = Config.get("web.browser");
        if (!"chrome".equalsIgnoreCase(browser)) {
            throw new IllegalArgumentException("Only chrome is configured сейчас. Запрошен: " + browser);
        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openBase() {
        driver.get(Config.get("web.baseUrl"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}