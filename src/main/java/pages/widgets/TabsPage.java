package pages.widgets;

import org.openqa.selenium.By;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class TabsPage {

	private By headerText = By.xpath("//h1[text()='Tabs']");

	private By whatTab = By.id("demo-tab-what");
	private By originTab = By.id("demo-tab-origin");
	private By useTab = By.id("demo-tab-use");
	private By moreTab = By.id("demo-tab-more");

	private By whatContent = By.id("demo-tabpane-what");
	private By originContent = By.id("demo-tabpane-origin");
	private By useContent = By.id("demo-tabpane-use");

	public boolean isTabsPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void clickWhatTab() {
		Log.info("Clicking What tab");
		WaitUtils.waitForElementClickable(whatTab).click();
	}

	public void clickOriginTab() {
		Log.info("Clicking Origin tab");
		WaitUtils.waitForElementClickable(originTab).click();
	}

	public void clickUseTab() {
		Log.info("Clicking Use tab");
		WaitUtils.waitForElementClickable(useTab).click();
	}

	public boolean isWhatTabSelected() {
		return isTabSelected(whatTab);
	}

	public boolean isOriginTabSelected() {
		return isTabSelected(originTab);
	}

	public boolean isUseTabSelected() {
		return isTabSelected(useTab);
	}

	public boolean isMoreTabDisabled() {
		return DriverManager.getDriver().findElement(moreTab).getAttribute("aria-disabled").equalsIgnoreCase("true");
	}

	private boolean isTabSelected(By tab) {
		return DriverManager.getDriver().findElement(tab).getAttribute("aria-selected").equalsIgnoreCase("true");
	}

	public String getWhatTabText() {
		return WaitUtils.waitForElementVisible(whatContent).getText();
	}

	public String getOriginTabText() {
		return WaitUtils.waitForElementVisible(originContent).getText();
	}

	public String getUseTabText() {
		return WaitUtils.waitForElementVisible(useContent).getText();
	}
}
