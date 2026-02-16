package pages.elements;

import org.openqa.selenium.By;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class RadioButtonPage {

	private By headerText = By.xpath("//h1[text()='Radio Button']");

	private By yesLabel = By.xpath("//label[@for='yesRadio']");
	private By impressiveLabel = By.xpath("//label[@for='impressiveRadio']");

	private By noInput = By.id("noRadio");
	private By resultText = By.xpath("//span[@class='text-success']");

	public boolean isRadioButtonPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void selectYes() {
		Log.info("Selecting Yes radio button");
		WaitUtils.scrollIntoView(yesLabel);
		WaitUtils.safeClick(yesLabel);
	}

	public void selectImpressive() {
		Log.info("Selecting Impressive radio button");
		WaitUtils.scrollIntoView(impressiveLabel);
		WaitUtils.safeClick(impressiveLabel);
	}

	public boolean isResultDisplayed(String expected) {
		return WaitUtils.waitForElementVisible(resultText).getText().equalsIgnoreCase(expected);
	}

	public boolean isNoRadioDisabled() {
		return !DriverManager.getDriver().findElement(noInput).isEnabled();
	}
}
