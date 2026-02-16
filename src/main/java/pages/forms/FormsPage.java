package pages.forms;

import org.openqa.selenium.By;

import utils.WaitUtils;

public class FormsPage {
	private By headerText = By.xpath("//div[@class='header-text' and text()='Forms']");
	private By practiceForm = By.xpath("//li[@id='item-0' ]//span[text()='Practice Form']");
	
	public boolean isFormsPageLoaded() {
		return WaitUtils.waitForElementVisible( headerText).isDisplayed();
	}
	public void clickPracticeForm() {
		WaitUtils.waitForElementClickable( practiceForm).click();
	}
}
