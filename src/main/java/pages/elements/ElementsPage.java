package pages.elements;

import org.openqa.selenium.By;

import utils.WaitUtils;

public class ElementsPage {

	private By headerText = By.xpath("//div[@class='header-text' and text()='Elements']");
	private By textBoxMenu = By.xpath("//li[@id='item-0' ]//span[text()='Text Box']");
	private By checkBoxMenu = By.xpath("//li[@id='item-1' ]//span[text()='Check Box']");
	private By radioButtonMenu = By.xpath("//li[@id='item-2' ]//span[text()='Radio Button']");
	private By webTablesMenu = By.xpath("//li[@id='item-3' ]//span[text()='Web Tables']");
	private By buttonsMenu = By.xpath("//li[@id='item-4' ]//span[text()='Buttons']");
	
	public boolean isElementsPageLoaded() {
		return WaitUtils.waitForElementVisible( headerText).isDisplayed();
	}
	public void clickTextBox() {
		WaitUtils.waitForElementClickable( textBoxMenu).click();
	}
	public void clickCheckBox() {
		WaitUtils.waitForElementClickable( checkBoxMenu).click();
	}
	public void clickRadioButton() {
		WaitUtils.waitForElementClickable( radioButtonMenu).click();
	}
	public void clickWebTables() {
		WaitUtils.waitForElementClickable( webTablesMenu).click();
	}
	public void clickButtons() {
		WaitUtils.waitForElementClickable( buttonsMenu).click();
	}
}
