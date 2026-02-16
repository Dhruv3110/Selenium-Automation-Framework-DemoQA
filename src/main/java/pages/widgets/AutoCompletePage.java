package pages.widgets;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import utils.DriverManager;
import utils.WaitUtils;

public class AutoCompletePage {

	/* ------------------------- LOCATORS ------------------------- */

	private final By headerText = By.xpath("//h1[text()='Auto Complete']");

	private final By multiInput = By.id("autoCompleteMultipleInput");
	private final By singleInput = By.id("autoCompleteSingleInput");

	private final By multiValueContainer = By.cssSelector(".auto-complete__multi-value");
	private final By multiValueLabel = By.cssSelector(".auto-complete__multi-value__label");
	private final By multiValueRemove = By.cssSelector(".auto-complete__multi-value__remove");

	private final By singleValue = By.cssSelector(".auto-complete__single-value");
	private final By autoCompleteControl = By.cssSelector(".auto-complete__control");

	/* ------------------------- PAGE STATE ------------------------- */

	public boolean isAutoCompletePageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	/* ------------------------- MULTI SELECT ------------------------- */

	public void selectMultipleColor(String color) {

		WebElement input = WaitUtils.waitForElementVisible(multiInput);
		input.sendKeys(color);
		input.sendKeys(Keys.ENTER);
		WaitUtils.waitForCondition(() -> DriverManager.getDriver().findElements(multiValueContainer).stream()
				.anyMatch(e -> e.findElement(multiValueLabel).getText().equalsIgnoreCase(color)));
	}

	public boolean isMultiColorSelected(String color) {

		return DriverManager.getDriver().findElements(multiValueContainer).stream()
				.anyMatch(e -> e.findElement(multiValueLabel).getText().equalsIgnoreCase(color));
	}

	public void removeMultiColor(String color) {

		List<WebElement> values = DriverManager.getDriver().findElements(multiValueContainer);

		for (WebElement value : values) {

			String label = value.findElement(multiValueLabel).getText();

			if (label.equalsIgnoreCase(color)) {

				WaitUtils.scrollIntoView(multiValueRemove);
				WaitUtils.safeClick(multiValueRemove);
				WaitUtils.waitForCondition(() -> DriverManager.getDriver().findElements(multiValueContainer).stream()
						.noneMatch(e -> e.findElement(multiValueLabel).getText().equalsIgnoreCase(color)));
				return;
			}
		}

		throw new RuntimeException("Color not found to remove: " + color);
	}

	/* ------------------------- SINGLE SELECT ------------------------- */

	public void selectSingleColor(String color) {
		WaitUtils.waitForElementClickable(autoCompleteControl).click();

		WebElement input = WaitUtils.waitForElementPresent(singleInput);
		input.sendKeys(Keys.CONTROL, "a");
		input.sendKeys(Keys.BACK_SPACE);

		input.sendKeys(color);
		input.sendKeys(Keys.ENTER);
		WaitUtils
				.waitForCondition(() -> WaitUtils.waitForElementVisible(singleValue).getText().equalsIgnoreCase(color));
	}

	public boolean isSingleColorSelected(String color) {
		return WaitUtils.waitForElementVisible(singleValue).getText().equalsIgnoreCase(color);
	}
}
