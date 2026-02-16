package pages.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class HomePage {

    private By elementsCard = By.xpath("//h5[contains(text(),'Elements')]");
    private By formsCard = By.xpath("//h5[contains(text(),'Forms')]");
    private By alertsFrameWindowsCard =
            By.xpath("//h5[text()='Alerts, Frame & Windows']");
    private By widgetsCard = By.xpath("//h5[contains(text(),'Widgets')]");

    public void clickElementsCard() {
        Log.info("Clicking on Elements Card");

        WaitUtils.scrollIntoView(elementsCard);
        try {
            WaitUtils.waitForElementClickable(elementsCard).click();
        } catch (Exception e) {
            Log.warn("Normal click failed, using JavaScript click");
            JavascriptExecutor js =
                    (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript(
                    "arguments[0].click();",
                    DriverManager.getDriver().findElement(elementsCard)
            );
        }
    }

    public void clickFormsCard() {
        Log.info("Clicking on Forms Card");

        WaitUtils.scrollIntoView(formsCard);
        WaitUtils.waitForElementClickable(formsCard).click();
    }

    public void clickAlertsFrameWindowsCard() {
        Log.info("Clicking on Alerts, Frame & Windows Card");

        WaitUtils.scrollIntoView(alertsFrameWindowsCard);
        WaitUtils.waitForElementClickable(alertsFrameWindowsCard).click();
    }

    public void clickWidgetsCard() {
        Log.info("Clicking on Widgets Card");

        WaitUtils.scrollIntoView(widgetsCard);
        WaitUtils.waitForElementClickable(widgetsCard).click();
    }
}
