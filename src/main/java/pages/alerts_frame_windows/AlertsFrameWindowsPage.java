package pages.alerts_frame_windows;

import org.openqa.selenium.By;

import utils.Log;
import utils.WaitUtils;

public class AlertsFrameWindowsPage {

	private By headerText = By.xpath("//div[@class='header-text' and text()='Alerts, Frame & Windows']");
	private By browserWindowsMenu = By.xpath("//li[@id='item-0']//span[text()='Browser Windows']");
	private By alertsMenu = By.xpath("//li[@id='item-1']//span[text()='Alerts']");
	private By framesMenu = By.xpath("//li[@id='item-2']//span[text()='Frames']");
	private By nestedFramesMenu = By.xpath("//li[@id='item-3']//span[text()='Nested Frames']");

	public boolean isPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void clickAlerts() {
		Log.info("Clicking Alerts menu");
		isPageLoaded();
	    WaitUtils.scrollIntoView(alertsMenu);
	    WaitUtils.safeClick(alertsMenu);
	}

	public void clickBrowserWindows() {
		Log.info("Clicking Browser Windows menu");
		WaitUtils.waitForElementClickable(browserWindowsMenu).click();
	}

	public void clickFrames() {
		Log.info("Clicking Frames menu");
		WaitUtils.scrollIntoView(framesMenu);
	    WaitUtils.safeClick(framesMenu);
	}

	public void clickNestedFrames() {
		Log.info("Clicking Nested Frames menu");
		WaitUtils.scrollIntoView(nestedFramesMenu);
		WaitUtils.safeClick(nestedFramesMenu);
	}
}
