package tests.elements;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.elements.ElementsPage;
import pages.elements.WebTablesPage;
import pages.homepage.HomePage;
import utils.Log;

public class WebTablesTest extends BaseTest {

    /* ---------------- HELPER ---------------- */

    private WebTablesPage openWebTablesPage() {
        new HomePage().clickElementsCard();
        new ElementsPage().clickWebTables();

        WebTablesPage page = new WebTablesPage();
        Assert.assertTrue(
                page.isWebTablesPageLoaded(),
                "Web Tables page not loaded"
        );
        return page;
    }

    /* ---------------- TESTS ---------------- */

    @Test(description = "TC_WT_001 - Verify navigation to Web Tables page")
    public void verifyWebTablesNavigation() {

        Log.info("Starting TC_WT_001");

        WebTablesPage webTablesPage = openWebTablesPage();

        Assert.assertTrue(
                webTablesPage.isWebTablesPageLoaded(),
                "Web Tables page not Loaded"
        );

        Log.info("TC_WT_001 Passed");
    }

    @Test(description = "TC_WT_002 - Verify adding a new record")
    public void verifyAddNewRecord() {

        Log.info("Starting TC_WT_002");

        WebTablesPage webTablesPage = openWebTablesPage();
        String email = "add@test.com";

        webTablesPage.clickAddButton();
        webTablesPage.fillRegistrationForm(
                "Add", "User", email, "25", "50000", "QA"
        );
        webTablesPage.submitForm();

        Assert.assertTrue(
                webTablesPage.isRecordPresentByEmail(email),
                "Newly added record not found in table"
        );

        Log.info("TC_WT_002 Passed");
    }

    @Test(description = "TC_WT_003 - Verify editing an existing record")
    public void verifyEditRecord() {

        Log.info("Starting TC_WT_003");

        WebTablesPage webTablesPage = openWebTablesPage();
        String email = "cierra@example.com";

        webTablesPage.editRecordByEmail(email);
        webTablesPage.fillRegistrationForm(
                "Cierra", "VegaUpdated", email, "30", "90000", "Engineering"
        );
        webTablesPage.submitForm();

        Assert.assertTrue(
                webTablesPage.isRecordPresent("VegaUpdated"),
                "Edited last name not updated in table"
        );

        Log.info("TC_WT_003 Passed");
    }

    @Test(description = "TC_WT_004 - Verify deleting a record")
    public void verifyDeleteRecord() {

        Log.info("Starting TC_WT_004");

        WebTablesPage webTablesPage = openWebTablesPage();
        String email = "alden@example.com";

        webTablesPage.deleteRecordByEmail(email);

        Assert.assertFalse(
                webTablesPage.isRecordPresentByEmail(email),
                "Deleted record still present in table"
        );

        Log.info("TC_WT_004 Passed");
    }

    @Test(description = "TC_WT_005 - Verify search functionality")
    public void verifySearchRecord() {

        Log.info("Starting TC_WT_005");

        WebTablesPage webTablesPage = openWebTablesPage();
        webTablesPage.searchRecord("Cierra");

        Assert.assertEquals(
                webTablesPage.getRowCount(),
                1,
                "Search should return exactly one row"
        );
        Assert.assertTrue(
                webTablesPage.isRecordPresent("Cierra"),
                "Search result not displayed correctly"
        );

        Log.info("TC_WT_005 Passed");
    }

    @Test(description = "TC_WT_006 - Validate add and delete consistency")
    public void verifyRowCountConsistencyAddDelete() {

        Log.info("Starting TC_WT_006");

        WebTablesPage webTablesPage = openWebTablesPage();
        String email = "flow@test.com";

        // ADD
        webTablesPage.clickAddButton();
        webTablesPage.fillRegistrationForm(
                "Flow", "Test", email, "28", "70000", "QA"
        );
        webTablesPage.submitForm();

        Assert.assertTrue(
                webTablesPage.isRecordPresentByEmail(email),
                "Record not present after add"
        );

        // DELETE
        webTablesPage.deleteRecordByEmail(email);

        Assert.assertFalse(
                webTablesPage.isRecordPresentByEmail(email),
                "Record still present after delete"
        );

        Log.info("TC_WT_006 Passed");
    }
}
