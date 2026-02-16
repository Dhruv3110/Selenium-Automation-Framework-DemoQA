package tests.alerts_frame_windows;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.alerts_frame_windows.AlertsFrameWindowsPage;
import pages.alerts_frame_windows.NestedFramesPage;
import pages.homepage.HomePage;
import utils.Log;

public class NestedFramesTest extends BaseTest {

	/* TC_NF_001 */
	@Test(description = "TC_NF_001 - Verify Nested Frames page loaded")
	public void verifyNestedFramesPageLoaded() {

		Log.info("Starting TC_NF_001");

		new HomePage().clickAlertsFrameWindowsCard();
		new AlertsFrameWindowsPage().clickNestedFrames();

		NestedFramesPage page = new NestedFramesPage();
		Assert.assertTrue(page.isNestedFramesPageLoaded(), "Nested Frames page not loaded");

		Log.info("TC_NF_001 Passed");
	}

	/* TC_NF_002 */
	@Test(description = "TC_NF_002 - Verify Parent frame text")
	public void verifyParentFrameText() {

		Log.info("Starting TC_NF_002");

		new HomePage().clickAlertsFrameWindowsCard();
		new AlertsFrameWindowsPage().clickNestedFrames();

		NestedFramesPage page = new NestedFramesPage();

		page.switchToParentFrame();
		Assert.assertTrue(page.getParentFrameText().contains("Parent frame"), "Parent frame text mismatch");

		page.switchToMainPage();

		Log.info("TC_NF_002 Passed");
	}

	/* TC_NF_003 */
	@Test(description = "TC_NF_003 - Verify Child frame text")
	public void verifyChildFrameText() {

		Log.info("Starting TC_NF_003");

		new HomePage().clickAlertsFrameWindowsCard();
		new AlertsFrameWindowsPage().clickNestedFrames();

		NestedFramesPage page = new NestedFramesPage();

		page.switchToParentFrame();
		page.switchToChildFrame();

		Assert.assertEquals(page.getChildFrameText(), "Child Iframe", "Child frame text mismatch");

		page.switchToMainPage();

		Log.info("TC_NF_003 Passed");
	}

	/* TC_NF_004 */
	@Test(description = "TC_NF_004 - Switch back to main page")
	public void verifySwitchBackToMainPage() {

		Log.info("Starting TC_NF_004");

		new HomePage().clickAlertsFrameWindowsCard();
		new AlertsFrameWindowsPage().clickNestedFrames();

		NestedFramesPage page = new NestedFramesPage();

		page.switchToParentFrame();
		page.switchToChildFrame();
		page.switchToMainPage();

		Assert.assertTrue(page.isNestedFramesPageLoaded(), "Failed to switch back to main page");

		Log.info("TC_NF_004 Passed");
	}
}
