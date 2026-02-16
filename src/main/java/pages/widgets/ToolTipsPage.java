package pages.widgets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class ToolTipsPage {

    private By headerText = By.xpath("//h1[text()='Tool Tips']");

    private By button = By.id("toolTipButton");
    private By textField = By.id("toolTipTextField");
    private By contraryText = By.xpath("//a[text()='Contrary']");
    private By numberText = By.xpath("//a[text()='1.10.32']");

    public boolean isToolTipsPageLoaded() {
        return WaitUtils.waitForElementVisible(headerText).isDisplayed();
    }

    /* ============ UNIVERSAL TOOLTIP HANDLER ============ */

    private String hoverAndGetTooltipText(By target) {

        Log.info("Hovering over element: " + target);

        WebElement element = WaitUtils.waitForElementPresent(target);
        WaitUtils.scrollIntoView(target);

        new Actions(DriverManager.getDriver())
                .moveToElement(element)
                .pause(Duration.ofMillis(500))
                .perform();

        String tooltipId = element.getAttribute("aria-describedby");

        if (tooltipId == null || tooltipId.isEmpty()) {
            throw new RuntimeException(
                "aria-describedby not found for element: " + target);
        }

        By tooltipBy = By.id(tooltipId);
        WebElement tooltipElement =
                WaitUtils.waitForElementVisible(tooltipBy);

        String text = tooltipElement.getText();
        Log.info("Tooltip text: " + text);

        return text;
    }

    /* ================= PUBLIC METHODS ================= */

    public String getButtonTooltipText() {
        return hoverAndGetTooltipText(button);
    }

    public String getTextFieldTooltipText() {
        return hoverAndGetTooltipText(textField);
    }

    public String getContraryTooltipText() {
        return hoverAndGetTooltipText(contraryText);
    }

    public String getNumberTooltipText() {
        return hoverAndGetTooltipText(numberText);
    }
}
