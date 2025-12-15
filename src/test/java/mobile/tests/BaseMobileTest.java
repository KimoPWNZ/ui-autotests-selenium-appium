package mobile.tests;

import config.Config;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;

public abstract class BaseMobileTest {
    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", Config.get("android.platformName"));
        caps.setCapability("deviceName", Config.get("android.deviceName"));
        caps.setCapability("automationName", Config.get("android.automationName"));

        caps.setCapability("appPackage", Config.get("android.appPackage"));
        caps.setCapability("appActivity", Config.get("android.appActivity"));

        caps.setCapability("newCommandTimeout", 180);
        caps.setCapability("noReset", true);              // не сбрасывать состояние приложения
        caps.setCapability("fullReset", false);
        caps.setCapability("autoGrantPermissions", true); // если запросы permissions появятся
        caps.setCapability("uiautomator2ServerInstallTimeout", 120000);
        caps.setCapability("adbExecTimeout", 120000);

        driver = new AndroidDriver(new URL(Config.get("appium.serverUrl")), caps);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}