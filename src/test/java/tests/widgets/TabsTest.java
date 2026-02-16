package tests.widgets;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import pages.widgets.TabsPage;
import pages.widgets.WidgetsPage;
import utils.Log;

public class TabsTest extends BaseTest {

    @Test(description = "TC_TB_001 - Verify Tabs page loaded")
    public void verifyTabsPageLoaded() {

        Log.info("Starting TC_TB_001");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickTabs();

        TabsPage page = new TabsPage();
        Assert.assertTrue(page.isTabsPageLoaded());

        Log.info("TC_TB_001 Passed");
    }

    @Test(description = "TC_TB_002 - Verify default selected tab")
    public void verifyDefaultTabSelected() {

        Log.info("Starting TC_TB_002");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickTabs();

        TabsPage page = new TabsPage();

        Assert.assertTrue(page.isWhatTabSelected(),
                "What tab should be selected by default");

        Assert.assertFalse(page.getWhatTabText().isEmpty(),
                "What tab content should be visible");

        Log.info("TC_TB_002 Passed");
    }

    @Test(description = "TC_TB_003 - Switch to Origin tab")
    public void switchToOriginTab() {

        Log.info("Starting TC_TB_003");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickTabs();

        TabsPage page = new TabsPage();

        page.clickOriginTab();

        Assert.assertTrue(page.isOriginTabSelected(),
                "Origin tab should be selected");

        Assert.assertFalse(page.getOriginTabText().isEmpty(),
                "Origin tab content should be visible");

        Log.info("TC_TB_003 Passed");
    }

    @Test(description = "TC_TB_004 - Switch to Use tab")
    public void switchToUseTab() {

        Log.info("Starting TC_TB_004");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickTabs();

        TabsPage page = new TabsPage();

        page.clickUseTab();

        Assert.assertTrue(page.isUseTabSelected(),
                "Use tab should be selected");

        Assert.assertFalse(page.getUseTabText().isEmpty(),
                "Use tab content should be visible");

        Log.info("TC_TB_004 Passed");
    }

    @Test(description = "TC_TB_005 - Verify More tab is disabled")
    public void verifyMoreTabDisabled() {

        Log.info("Starting TC_TB_005");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickTabs();

        TabsPage page = new TabsPage();

        Assert.assertTrue(page.isMoreTabDisabled(),
                "More tab should be disabled");

        Log.info("TC_TB_005 Passed");
    }
}
