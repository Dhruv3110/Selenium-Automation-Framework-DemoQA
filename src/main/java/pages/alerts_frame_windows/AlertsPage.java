package pages.alerts_frame_windows;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import utils.WaitUtils;

public class AlertsPage {

    /* ------------------------- LOCATORS ------------------------- */

    private final By headerText = By.xpath("//h1[text()='Alerts']");

    private final By simpleAlertBtn = By.id("alertButton");
    private final By delayedAlertBtn = By.id("timerAlertButton");
    private final By confirmAlertBtn = By.id("confirmButton");
    private final By promptAlertBtn = By.id("promtButton");

    private final By confirmResultText = By.id("confirmResult");
    private final By promptResultText = By.id("promptResult");

    /* ------------------------- PAGE STATE ------------------------- */

    public boolean isAlertsPageLoaded() {
        return WaitUtils.waitForElementVisible(headerText).isDisplayed();
    }

    /**
     * Used after closing alerts that do not leave UI feedback
     * (simple & delayed alerts).
     */
    public boolean isPageStable() {
        return WaitUtils.waitForElementVisible(headerText).isDisplayed();
    }

    /* ------------------------- ACTIONS ------------------------- */

    public void triggerSimpleAlert() {
        WaitUtils.scrollIntoView(simpleAlertBtn);
        WaitUtils.safeClick(simpleAlertBtn);
    }

    public void triggerDelayedAlert() {
        WaitUtils.scrollIntoView(delayedAlertBtn);
        WaitUtils.safeClick(delayedAlertBtn);
    }

    public void triggerConfirmAlert() {
        WaitUtils.scrollIntoView(confirmAlertBtn);
        WaitUtils.safeClick(confirmAlertBtn);
    }

    public void triggerPromptAlert() {
        WaitUtils.scrollIntoView(promptAlertBtn);
        WaitUtils.safeClick(promptAlertBtn);
    }

    /* ------------------------- ALERT HANDLING ------------------------- */

    public void acceptAlert() {
        WaitUtils.waitForAlert().accept();
    }

    /**
     * Delayed alert appears after ~5 seconds on DemoQA.
     * We explicitly wait with timeout instead of relying on timing.
     */
    public void acceptDelayedAlert() {
        WaitUtils.waitForAlertWithTimeout(10).accept();
    }

    public void dismissAlert() {
        WaitUtils.waitForAlert().dismiss();
    }

    public void enterTextInPrompt(String text) {
        Alert alert = WaitUtils.waitForAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    /* ------------------------- RESULT VALIDATION ------------------------- */

    public String getConfirmResult() {
        return WaitUtils.waitForElementVisible(confirmResultText).getText();
    }

    public String getPromptResult() {
        return WaitUtils.waitForElementVisible(promptResultText).getText();
    }
}
