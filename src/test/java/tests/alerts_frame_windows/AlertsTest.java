package tests.alerts_frame_windows;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.alerts_frame_windows.AlertsFrameWindowsPage;
import pages.alerts_frame_windows.AlertsPage;
import pages.homepage.HomePage;
import utils.Log;

public class AlertsTest extends BaseTest {

	private void navigateToAlertsPage() {
		Log.info("Navigating to Alerts page");
		new HomePage().clickAlertsFrameWindowsCard();
		new AlertsFrameWindowsPage().clickAlerts();
	}

	@Test(description = "TC_A_001 - Verify navigation to Alerts page")
	public void verifyAlertsPageLoaded() {

		Log.info("TC_A_001 STARTED - Verify Alerts page loaded");

		navigateToAlertsPage();

		AlertsPage alertsPage = new AlertsPage();
		Assert.assertTrue(alertsPage.isAlertsPageLoaded(), "Alerts page is not loaded");

		Log.info("TC_A_001 PASSED");
	}

	@Test(description = "TC_A_002 - Handle simple alert")
	public void handleSimpleAlert() {

		Log.info("TC_A_002 STARTED - Handle simple alert");

		navigateToAlertsPage();

		AlertsPage alertsPage = new AlertsPage();
		alertsPage.triggerSimpleAlert();
		alertsPage.acceptAlert();

		Assert.assertTrue(alertsPage.isPageStable(), "Page not stable after closing simple alert");

		Log.info("TC_A_002 PASSED");
	}

	@Test(description = "TC_A_003 - Handle confirmation alert")
	public void handleConfirmationAlert() {

		Log.info("TC_A_003 STARTED - Handle confirmation alert");

		navigateToAlertsPage();

		AlertsPage alertsPage = new AlertsPage();

		alertsPage.triggerConfirmAlert();
		alertsPage.acceptAlert();
		Assert.assertTrue(alertsPage.getConfirmResult().contains("Ok"), "Confirmation OK result not displayed");

		alertsPage.triggerConfirmAlert();
		alertsPage.dismissAlert();
		Assert.assertTrue(alertsPage.getConfirmResult().contains("Cancel"), "Confirmation Cancel result not displayed");

		Log.info("TC_A_003 PASSED");
	}

	@Test(description = "TC_A_004 - Handle prompt alert")
	public void handlePromptAlert() {

		Log.info("TC_A_004 STARTED - Handle prompt alert");

		navigateToAlertsPage();

		AlertsPage alertsPage = new AlertsPage();
		String inputText = "Dhruv Gupta";

		alertsPage.triggerPromptAlert();
		alertsPage.enterTextInPrompt(inputText);

		Assert.assertTrue(alertsPage.getPromptResult().contains(inputText),
				"Prompt alert text not displayed correctly");

		Log.info("TC_A_004 PASSED");
	}

	@Test(description = "TC_A_005 - Verify delayed alert")
	public void handleDelayedAlert() {

		Log.info("TC_A_005 STARTED - Handle delayed alert");

		navigateToAlertsPage();

		AlertsPage alertsPage = new AlertsPage();
		alertsPage.triggerDelayedAlert();
		alertsPage.acceptDelayedAlert();

		Assert.assertTrue(alertsPage.isPageStable(), "Page not stable after closing delayed alert");

		Log.info("TC_A_005 PASSED");
	}
}
