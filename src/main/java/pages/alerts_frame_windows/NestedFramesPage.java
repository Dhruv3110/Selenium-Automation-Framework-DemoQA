package pages.alerts_frame_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class NestedFramesPage {

	private By headerText = By.xpath("//h1[text()='Nested Frames']");

	private By parentFrame = By.id("frame1");
	private By childFrame = By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']");
	private By parentText = By.xpath("//body[contains(text(),'Parent frame')]");
	private By childText = By.xpath("//p[text()='Child Iframe']");

	public boolean isNestedFramesPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void switchToParentFrame() {
		Log.info("Switching to Parent Frame");
		WebDriver driver = DriverManager.getDriver();
		driver.switchTo().frame(WaitUtils.waitForElementPresent(parentFrame));
	}

	public void switchToChildFrame() {
		Log.info("Switching to Child Frame");
		WebDriver driver = DriverManager.getDriver();
		driver.switchTo().frame(WaitUtils.waitForElementPresent(childFrame));
	}

	public String getParentFrameText() {
		return WaitUtils.waitForElementVisible(parentText).getText();
	}

	public String getChildFrameText() {
		return WaitUtils.waitForElementVisible(childText).getText();
	}

	public void switchToMainPage() {
		Log.info("Switching back to main content");
		DriverManager.getDriver().switchTo().defaultContent();
	}
}
