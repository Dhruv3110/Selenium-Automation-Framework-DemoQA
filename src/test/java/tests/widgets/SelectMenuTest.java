package tests.widgets;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import pages.widgets.SelectMenuPage;
import pages.widgets.WidgetsPage;
import utils.Log;

public class SelectMenuTest extends BaseTest {

    /* ---------------- HELPER ---------------- */

    private SelectMenuPage openSelectMenuPage() {
        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickSelectMenu();

        SelectMenuPage page = new SelectMenuPage();
        Assert.assertTrue(
                page.isSelectMenuPageLoaded(),
                "Select Menu page did not load"
        );
        return page;
    }

    /* ---------------- TESTS ---------------- */

    @Test(description = "TC_SM_001 - Verify Select Menu page loaded")
    public void verifySelectMenuPageLoaded() {

        Log.info("Starting TC_SM_001");

        openSelectMenuPage();

        Log.info("TC_SM_001 Passed");
    }

    @Test(description = "TC_SM_002 - Select value from Select Value dropdown")
    public void selectValueDropdown() {

        Log.info("Starting TC_SM_002");

        SelectMenuPage page = openSelectMenuPage();

        page.selectValue("Group 1, option 1");

        Assert.assertTrue(
                page.isSingleValueSelected("Group 1, option 1"),
                "Select Value dropdown did not select expected value"
        );

        Log.info("TC_SM_002 Passed");
    }

    @Test(description = "TC_SM_003 - Select value from Select One dropdown")
    public void selectOneDropdown() {

        Log.info("Starting TC_SM_003");

        SelectMenuPage page = openSelectMenuPage();

        page.selectOne("Dr.");

        Assert.assertTrue(
                page.isSelectOneValueSelected("Dr."),
                "Select One dropdown did not select expected value"
        );

        Log.info("TC_SM_003 Passed");
    }

    @Test(description = "TC_SM_004 - Select old style select menu")
    public void selectOldStyleMenu() {

        Log.info("Starting TC_SM_004");

        SelectMenuPage page = openSelectMenuPage();

        page.selectOldStyleValue("Purple");

        Assert.assertEquals(
                page.getOldStyleSelectedValue(),
                "Purple",
                "Old style select value mismatch"
        );

        Log.info("TC_SM_004 Passed");
    }

    @Test(description = "TC_SM_005 - Select multiple values from multi select")
    public void selectMultipleValues() {

        Log.info("Starting TC_SM_005");

        SelectMenuPage page = openSelectMenuPage();

        page.selectMultiple("Green");
        page.selectMultiple("Blue");

        Assert.assertTrue(page.isMultiValueSelected("Green"), "Green not selected");
        Assert.assertTrue(page.isMultiValueSelected("Blue"), "Blue not selected");

        Log.info("TC_SM_005 Passed");
    }

    @Test(description = "TC_SM_006 - Select multiple cars from standard multi select")
    public void selectMultipleCars() {

        Log.info("Starting TC_SM_006");

        SelectMenuPage page = openSelectMenuPage();

        page.selectMultipleCars(List.of("Volvo", "Audi"));

        Assert.assertEquals(
                page.getSelectedCars().size(),
                2,
                "Incorrect number of selected cars"
        );

        Log.info("TC_SM_006 Passed");
    }
}
