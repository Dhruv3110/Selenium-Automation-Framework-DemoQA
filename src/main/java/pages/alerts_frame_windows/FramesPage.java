package pages.alerts_frame_windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class FramesPage {

	private By headerText = By.xpath("//h1[text()='Frames']");

	private By frame1 = By.id("frame1");
	private By frame2 = By.id("frame2");

	private By frameHeading = By.id("sampleHeading");

	public boolean isFramesPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void switchToFrame1() {
		Log.info("Switching to Frame 1");
		WebDriver driver = DriverManager.getDriver();
		driver.switchTo().frame(WaitUtils.waitForElementPresent(frame1));
	}

	public void switchToFrame2() {
		Log.info("Switching to Frame 2");
		WebDriver driver = DriverManager.getDriver();
		driver.switchTo().frame(WaitUtils.waitForElementPresent(frame2));
	}

	public String getFrameText() {
		return WaitUtils.waitForElementVisible(frameHeading).getText();
	}

	public void switchToMainPage() {
		Log.info("Switching back to main page");
		DriverManager.getDriver().switchTo().defaultContent();
	}
}
