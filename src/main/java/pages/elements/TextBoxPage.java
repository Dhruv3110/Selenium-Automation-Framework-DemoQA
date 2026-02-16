package pages.elements;

import org.openqa.selenium.By;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class TextBoxPage {

	private By headerText = By.xpath("//h1[text()='Text Box']");
	private By fullName = By.id("userName");
	private By email = By.id("userEmail");
	private By currentAddress = By.id("currentAddress");
	private By permanentAddress = By.id("permanentAddress");
	private By submitBtn = By.id("submit");
	private By outputBox = By.id("output");

	public boolean isTextBoxPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void fillTextBoxForm(String name, String mail, String currAddr, String permAddr) {

		Log.info("Filling Text Box form");

		WaitUtils.waitForElementVisible(fullName).sendKeys(name);
		WaitUtils.waitForElementVisible(email).sendKeys(mail);
		WaitUtils.waitForElementVisible(currentAddress).sendKeys(currAddr);
		WaitUtils.waitForElementVisible(permanentAddress).sendKeys(permAddr);
	}

	public void submitForm() {
		Log.info("Submitting Text Box form");

		WaitUtils.scrollIntoView(submitBtn);
		try {
			WaitUtils.waitForElementClickable(submitBtn).click();
		} catch (Exception e) {
			Log.warn("Normal click failed, using JavaScript click");
			WaitUtils.safeClick(submitBtn);
		}
	}

	public boolean isEmailFieldInErrorState() {
		String classAttribute = DriverManager.getDriver().findElement(email).getAttribute("class");

		return classAttribute.contains("field-error");
	}

	public boolean isOutputDisplayed() {
		return WaitUtils.waitForElementVisible(outputBox).isDisplayed();
	}
}
