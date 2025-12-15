package mobile.screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class WikipediaOnboarding {
    private final AndroidDriver driver;

    private final By skipButton = By.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private final By doneButton = By.id("org.wikipedia:id/fragment_onboarding_done_button");
    private final By nextButton = By.id("org.wikipedia:id/fragment_onboarding_forward_button");

    public WikipediaOnboarding(AndroidDriver driver) {
        this.driver = driver;
    }

    public void skipIfPresent() {
        clickIfExists(skipButton);
        for (int i = 0; i < 4; i++) {
            if (!clickIfExists(nextButton)) break;
        }
        clickIfExists(doneButton);
        clickIfExists(skipButton);
    }

    private boolean clickIfExists(By locator) {
        try {
            var els = driver.findElements(locator);
            if (els.isEmpty()) return false;
            els.get(0).click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}