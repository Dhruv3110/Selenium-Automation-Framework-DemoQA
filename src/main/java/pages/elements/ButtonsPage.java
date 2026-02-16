package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class ButtonsPage {

	private By headerText = By.xpath("//h1[text()='Buttons']");

	private By doubleClickBtn = By.id("doubleClickBtn");
	private By rightClickBtn = By.id("rightClickBtn");
	private By dynamicClickBtn = By.xpath("//button[text()='Click Me']");

	private By doubleClickMsg = By.id("doubleClickMessage");
	private By rightClickMsg = By.id("rightClickMessage");
	private By dynamicClickMsg = By.id("dynamicClickMessage");

	public boolean isButtonPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void doubleClickButton() {

		WebDriver driver = DriverManager.getDriver();
		WebElement element = WaitUtils.waitForElementVisible(doubleClickBtn);
		WaitUtils.scrollIntoView(doubleClickBtn);
		new Actions(driver).moveToElement(element).pause(200).doubleClick().perform();
		WaitUtils.waitForCondition(this::isDoubleClickMessageDisplayed);
	}

	public void rightClickButton() {

		WebDriver driver = DriverManager.getDriver();
		WebElement element = WaitUtils.waitForElementVisible(rightClickBtn);
		WaitUtils.scrollIntoView(rightClickBtn);
		new Actions(driver).moveToElement(element).pause(200).contextClick().perform();
		WaitUtils.waitForCondition(this::isRightClickMessageDisplayed);
	}

	public void clickDynamicButton() {

	    WaitUtils.scrollIntoView(dynamicClickBtn);
	    WaitUtils.safeClick(dynamicClickBtn);
	    WaitUtils.waitForCondition(this::isDynamicClickMessageDisplayed);
	}


	public boolean isDoubleClickMessageDisplayed() {
		try {
			return WaitUtils.waitForElementVisible(doubleClickMsg).getText().toLowerCase().contains("double click");
		} catch (Exception e) {
			Log.warn("Double click message not displayed (known DemoQA behavior)");
			return false;
		}
	}

	public boolean isDoubleClickButtonEnabled() {
		return WaitUtils.waitForElementVisible(doubleClickBtn).isEnabled();
	}

	public boolean isRightClickMessageDisplayed() {
		return WaitUtils.waitForElementVisible(rightClickMsg).isDisplayed();
	}

	public boolean isDynamicClickMessageDisplayed() {
		return WaitUtils.waitForElementVisible(dynamicClickMsg).isDisplayed();
	}
}
