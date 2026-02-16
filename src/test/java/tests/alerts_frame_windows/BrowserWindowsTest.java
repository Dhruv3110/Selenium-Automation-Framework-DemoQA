package tests.alerts_frame_windows;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.alerts_frame_windows.AlertsFrameWindowsPage;
import pages.alerts_frame_windows.BrowserWindowsPage;
import pages.homepage.HomePage;
import utils.Log;
import utils.WaitUtils;
import utils.WindowUtils;

public class BrowserWindowsTest extends BaseTest {

    @Test(description = "TC_BW_001 - Verify Browser Windows page loaded")
    public void verifyBrowserWindowsPageLoaded() {

        Log.info("Starting TC_BW_001");

        new HomePage().clickAlertsFrameWindowsCard();
        new AlertsFrameWindowsPage().clickBrowserWindows();

        BrowserWindowsPage page = new BrowserWindowsPage();
        Assert.assertTrue(page.isBrowserWindowsPageLoaded(),
                "Browser Windows page not loaded");

        Log.info("TC_BW_001 Passed");
    }

    @Test(description = "TC_BW_002 - Verify New Tab")
    public void verifyNewTab() {

        Log.info("Starting TC_BW_002");

        new HomePage().clickAlertsFrameWindowsCard();
        new AlertsFrameWindowsPage().clickBrowserWindows();

        BrowserWindowsPage page = new BrowserWindowsPage();

        String parent = WindowUtils.getCurrentWindow();
        page.clickNewTab();
        WindowUtils.switchToNewWindow(parent);

        Assert.assertEquals(page.getNewPageText(),
                "This is a sample page",
                "New tab content mismatch");

        WindowUtils.closeCurrentWindow();
        WindowUtils.switchToParentWindow(parent);

        Log.info("TC_BW_002 Passed");
    }

    @Test(description = "TC_BW_003 - Verify New Window")
    public void verifyNewWindow() {

        Log.info("Starting TC_BW_003");

        new HomePage().clickAlertsFrameWindowsCard();
        new AlertsFrameWindowsPage().clickBrowserWindows();

        BrowserWindowsPage page = new BrowserWindowsPage();

        String parent = WindowUtils.getCurrentWindow();
        page.clickNewWindow();
        WindowUtils.switchToNewWindow(parent);

        Assert.assertEquals(page.getNewPageText(),
                "This is a sample page",
                "New window content mismatch");

        WindowUtils.closeCurrentWindow();
        WindowUtils.switchToParentWindow(parent);

        Log.info("TC_BW_003 Passed");
    }

    @Test(description = "TC_BW_004 - Verify New Window Message")
    public void verifyNewWindowMessage() {

        Log.info("Starting TC_BW_004");

        new HomePage().clickAlertsFrameWindowsCard();
        new AlertsFrameWindowsPage().clickBrowserWindows();

        BrowserWindowsPage page = new BrowserWindowsPage();

        String parent = WindowUtils.getCurrentWindow();
        int windowsBefore = WindowUtils.getWindowCount();

        page.clickNewWindowMessage();

        // wait until window count increases
        WaitUtils.waitForCondition(
                () -> WindowUtils.getWindowCount() > windowsBefore
        );


        WindowUtils.switchToNewWindow(parent);
        Assert.assertNotEquals(
                WindowUtils.getCurrentWindow(),
                parent,
                "New Window Message did not open"
        );

        WindowUtils.closeCurrentWindow();
        WindowUtils.switchToParentWindow(parent);

        Log.info("TC_BW_004 Passed");
    }
}
