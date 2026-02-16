package tests.alerts_frame_windows;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.alerts_frame_windows.AlertsFrameWindowsPage;
import pages.alerts_frame_windows.FramesPage;
import pages.homepage.HomePage;
import utils.Log;


public class FramesTest extends BaseTest{
	
	@Test(description = "TC_FR_001 - Verify Frames page loaded")
	public void verifyFramesPageLoaded() {
		Log.info("Starting TC_FR_001");
		
        new HomePage().clickAlertsFrameWindowsCard();
        new AlertsFrameWindowsPage().clickFrames();

        FramesPage framesPage = new FramesPage();
        Assert.assertTrue(framesPage.isFramesPageLoaded(), "Frames page not loaded");
        
        Log.info("TC_FR_001 Passed");
        
	}
	
    @Test(description = "TC_FR_002 - Verify Frame 1 text")
    public void verifyFrame1Text() {

        Log.info("Starting TC_FR_002");

        new HomePage().clickAlertsFrameWindowsCard();
        new AlertsFrameWindowsPage().clickFrames();

        FramesPage framesPage = new FramesPage();

        framesPage.switchToFrame1();
        Assert.assertEquals(
                framesPage.getFrameText(),
                "This is a sample page",
                "Frame 1 text mismatch"
        );

        framesPage.switchToMainPage();

        Log.info("TC_FR_002 Passed");
    }

    @Test(description = "TC_FR_003 - Verify Frame 2 text")
    public void verifyFrame2Text() {

        Log.info("Starting TC_FR_003");

        new HomePage().clickAlertsFrameWindowsCard();
        new AlertsFrameWindowsPage().clickFrames();

        FramesPage framesPage = new FramesPage();

        framesPage.switchToFrame2();
        Assert.assertEquals(
                framesPage.getFrameText(),
                "This is a sample page",
                "Frame 2 text mismatch"
        );

        framesPage.switchToMainPage();

        Log.info("TC_FR_003 Passed");
    }

    @Test(description = "TC_FR_004 - Verify switching back to main page")
    public void verifySwitchBackToMainPage() {

        Log.info("Starting TC_FR_004");

        new HomePage().clickAlertsFrameWindowsCard();
        new AlertsFrameWindowsPage().clickFrames();

        FramesPage framesPage = new FramesPage();

        framesPage.switchToFrame1();
        framesPage.switchToMainPage();

        Assert.assertTrue(
                framesPage.isFramesPageLoaded(),
                "Did not switch back to main page"
        );

        Log.info("TC_FR_004 Passed");
    }
}
