package pages.widgets;

import org.openqa.selenium.By;

import utils.Log;
import utils.WaitUtils;

public class WidgetsPage {

	private By headerText = By.xpath("//div[@class='header-text' and text()='Widgets']");
	private By accordianPage = By.xpath("//li[@id='item-0']//span[text()='Accordian']");
	private By autoCompletePage = By.xpath("//li[@id='item-1']//span[text()='Auto Complete']");
	private By datePickerPage = By.xpath("//li[@id='item-2']//span[text()='Date Picker']");
	private By sliderPage = By.xpath("//li[@id='item-3']//span[text()='Slider']");
	private By progressBarPage = By.xpath("//li[@id='item-4']//span[text()='Progress Bar']");
	private By tabsPage = By.xpath("//li[@id='item-5']//span[text()='Tabs']");
	private By toolTipsPage = By.xpath("//li[@id='item-6']//span[text()='Tool Tips']");
	private By menuPage = By.xpath("//li[@id='item-7']//span[text()='Menu']");
	private By selectMenuPage = By.xpath("//li[@id='item-8']//span[text()='Select Menu']");
	
	public boolean isWidgetsPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void clickAccordian() {
		Log.info("Navigating to Accordian");
		WaitUtils.waitForElementClickable(accordianPage).click();
	}

	public void clickAutoComplete() {
		Log.info("Navigating to Auto Complete");
		WaitUtils.scrollIntoView(autoCompletePage);
		WaitUtils.waitForElementClickable(autoCompletePage).click();
	}

	public void clickDatePicker() {
		Log.info("Navigating to Date Picker");
		WaitUtils.scrollIntoView(datePickerPage);
		WaitUtils.waitForElementClickable(datePickerPage).click();
	}

	public void clickSlider() {
		Log.info("Navigating to Slider");
		WaitUtils.scrollIntoView(sliderPage);
		WaitUtils.waitForElementClickable(sliderPage).click();
	}

	public void clickProgressBar() {
		Log.info("Navigating to Progress Bar");
		WaitUtils.scrollIntoView(progressBarPage);
		WaitUtils.waitForElementClickable(progressBarPage).click();
	}

	public void clickTabs() {
		Log.info("Navigating to Tabs");
		WaitUtils.scrollIntoView(tabsPage);
		WaitUtils.waitForElementClickable(tabsPage).click();
	}

	public void clickToolTips() {
		Log.info("Navigating to Tool Tips");
		WaitUtils.scrollIntoView(toolTipsPage);
		WaitUtils.waitForElementClickable(toolTipsPage).click();
	}

	public void clickMenu() {
		Log.info("Navigating to Menu");
		WaitUtils.scrollIntoView(menuPage);
		WaitUtils.waitForElementClickable(menuPage).click();
	}

	public void clickSelectMenu() {
		Log.info("Navigating to Select Menu");
		WaitUtils.scrollIntoView(selectMenuPage);
		WaitUtils.waitForElementClickable(selectMenuPage).click();
	}
}
