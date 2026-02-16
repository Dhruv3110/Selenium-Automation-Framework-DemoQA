package tests.widgets;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import pages.widgets.DatePickerPage;
import pages.widgets.WidgetsPage;
import utils.Log;

public class DatePickerTest extends BaseTest {

    @Test(description = "TC_DP_001 - Verify Date Picker page loaded")
    public void verifyDatePickerPageLoaded() {

        Log.info("Starting TC_DP_001");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickDatePicker();

        DatePickerPage page = new DatePickerPage();
        Assert.assertTrue(page.isDatePickerPageLoaded());

        Log.info("TC_DP_001 Passed");
    }

    @Test(description = "TC_DP_002 - Select specific date")
    public void selectSpecificDate() {

        Log.info("Starting TC_DP_002");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickDatePicker();

        DatePickerPage page = new DatePickerPage();

        page.selectDate("March", "2025", "15");

        Log.info("Verifying selected date");
        Assert.assertTrue(
                page.getSelectedDate().contains("03/15/2025"),
                "Date selection failed"
        );

        Log.info("TC_DP_002 Passed");
    }

    @Test(description = "TC_DP_003 - Change month and year")
    public void changeMonthAndYear() {

        Log.info("Starting TC_DP_003");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickDatePicker();

        DatePickerPage page = new DatePickerPage();

        page.selectDate("December", "2024", "25");

        Assert.assertTrue(
                page.getSelectedDate().contains("12/25/2024")
        );

        Log.info("TC_DP_003 Passed");
    }

    @Test(description = "TC_DP_004 - Select date and time")
    public void selectDateAndTime() {

        Log.info("Starting TC_DP_004");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickDatePicker();

        DatePickerPage page = new DatePickerPage();

        page.selectDateAndTime("July", "2026", "10", "10:30");

        Assert.assertTrue(
                page.getSelectedDateTime().contains("July"),
                "Date & time selection failed"
        );

        Log.info("TC_DP_004 Passed");
    }
}
