package tests.widgets;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import pages.widgets.ProgressBarPage;
import pages.widgets.WidgetsPage;
import utils.Log;

public class ProgressBarTest extends BaseTest {

	@Test(description = "TC_PB_001 - Verify Progress Bar page loaded")
	public void verifyProgressBarPageLoaded() {

		Log.info("Starting TC_PB_001");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickProgressBar();

		ProgressBarPage page = new ProgressBarPage();
		Assert.assertTrue(page.isProgressBarPageLoaded());

		Log.info("TC_PB_001 Passed");
	}

	@Test(description = "TC_PB_002 - Start progress bar")
	public void startProgressBar() {

		Log.info("Starting TC_PB_002");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickProgressBar();

		ProgressBarPage page = new ProgressBarPage();
		page.startProgressBar();

		int value = page.waitForProgressToReach(10);
		Assert.assertTrue(value >= 10);

		Log.info("TC_PB_002 Passed");
	}

	@Test(description = "TC_PB_003 - Stop progress bar at around 50%")
	public void stopProgressBarAtMid() {

		Log.info("Starting TC_PB_003");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickProgressBar();

		ProgressBarPage page = new ProgressBarPage();

		page.startProgressBar();
		page.waitForProgressToReach(45);
		page.stopProgressBar();

		int stoppedValue = page.getProgressValue();
		Assert.assertTrue(stoppedValue >= 40 && stoppedValue <= 65, "Progress not stopped in expected range");

		Log.info("TC_PB_003 Passed");
	}

	@Test(description = "TC_PB_004 - Complete progress bar")
	public void completeProgressBar() {

		Log.info("Starting TC_PB_004");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickProgressBar();

		ProgressBarPage page = new ProgressBarPage();

		page.startProgressBar();
		page.waitForProgressToComplete();

		Assert.assertTrue(page.getProgressValue() >= 99, "Progress bar did not complete");

		Log.info("TC_PB_004 Passed");
	}

	@Test(description = "TC_PB_005 - Reset progress bar")
	public void resetProgressBar() {

		Log.info("Starting TC_PB_005");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickProgressBar();

		ProgressBarPage page = new ProgressBarPage();

		page.startProgressBar();
		page.waitForProgressToComplete();
		page.resetProgressBar();
		page.waitForReset();

		Assert.assertTrue(page.isProgressReset(), "Progress bar was not reset correctly");

		Log.info("===== TC_PB_005 Passed =====");
	}
}
