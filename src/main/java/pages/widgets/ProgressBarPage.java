package pages.widgets;

import org.openqa.selenium.By;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class ProgressBarPage {

	private By headerText = By.xpath("//h1[text()='Progress Bar']");
	private By startStopButton = By.id("startStopButton");
	private By resetButton = By.id("resetButton");
	private By progressBarInner = By.cssSelector("#progressBar .progress-bar");

	/* ---------- Page Load ---------- */

	public boolean isProgressBarPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	/* ---------- Actions ---------- */

	public void startProgressBar() {
		Log.info("Starting progress bar");
		WaitUtils.scrollIntoView(startStopButton);
		WaitUtils.safeClick(startStopButton);
	}

	public void stopProgressBar() {
		Log.info("Stopping progress bar");
		WaitUtils.scrollIntoView(startStopButton);
		WaitUtils.safeClick(startStopButton);
	}

	public void resetProgressBar() {
		Log.info("Resetting progress bar");
		WaitUtils.waitForElementClickable(resetButton).click();
	}

	/* ---------- Progress ---------- */

	public int getProgressValue() {

		String text = DriverManager.getDriver().findElement(progressBarInner).getText(); // e.g. "75%"

		if (text == null || !text.contains("%")) {
			return 0;
		}

		return Integer.parseInt(text.replace("%", "").trim());
	}

	public int waitForProgressToReach(int expectedPercent) {

		Log.info("Waiting for progress to reach at least: " + expectedPercent + "%");

		WaitUtils.waitForCondition(() -> {
			int value = getProgressValue();
			return value >= expectedPercent;
		});

		return getProgressValue();
	}

	public void waitForProgressToComplete() {

		Log.info("Waiting for progress to complete (>= 99%)");

		WaitUtils.waitForCondition(() -> {
			int value = getProgressValue();
			return value >= 99;
		});
	}

	/* ---------- Reset ---------- */

	public boolean isProgressReset() {

		String buttonText = DriverManager.getDriver().findElement(startStopButton).getText();

		return getProgressValue() == 0 && buttonText.equalsIgnoreCase("Start");
	}

	public void waitForReset() {

		Log.info("Waiting for progress bar to reset");

		WaitUtils.waitForCondition(() -> {
			int value = getProgressValue();
			String text = DriverManager.getDriver().findElement(startStopButton).getText();

			Log.info("Reset check -> value: " + value + ", button: " + text);
			return value == 0 && text.equalsIgnoreCase("Start");
		});
	}
}
