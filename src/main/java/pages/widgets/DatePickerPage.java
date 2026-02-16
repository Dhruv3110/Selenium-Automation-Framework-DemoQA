package pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.DriverManager;
import utils.WaitUtils;

public class DatePickerPage {

    /* ---------------- LOCATORS ---------------- */

    private final By headerText = By.xpath("//h1[text()='Date Picker']");

    private final By selectDateInput = By.id("datePickerMonthYearInput");
    private final By dateTimeInput = By.id("dateAndTimePickerInput");

    private final By monthDropdown = By.className("react-datepicker__month-select");
    private final By yearDropdown = By.className("react-datepicker__year-select");

    private final By timeListItem = By.cssSelector(".react-datepicker__time-list-item");
    private final By dateTimeHeader = By.cssSelector(".react-datepicker__current-month");
    private final By nextMonthButton = By.cssSelector(".react-datepicker__navigation--next");

    private final String dayLocator =
            "//div[contains(@class,'react-datepicker__day') " +
            "and text()='%s' and not(contains(@class,'outside-month'))]";

    /* ---------------- PAGE STATE ---------------- */

    public boolean isDatePickerPageLoaded() {
        return WaitUtils.waitForElementVisible(headerText).isDisplayed();
    }

    /* ---------------- DATE ONLY ---------------- */

    public void selectDate(String month, String year, String day) {

        WaitUtils.waitForElementClickable(selectDateInput).click();

        selectMonth(month);
        selectYear(year);
        selectDay(day);
    }

    /* ---------------- DATE & TIME ---------------- */

    public void selectDateAndTime(String month, String year, String day, String time) {

        WaitUtils.waitForElementClickable(dateTimeInput).click();

        navigateToMonthYear(month, year);
        selectDay(day);
        selectTime(time);
    }

    /* ---------------- MONTH/YEAR NAVIGATION ---------------- */

    private void navigateToMonthYear(String expectedMonth, String expectedYear) {

        int maxTries = 24;

        for (int i = 0; i < maxTries; i++) {

            String header =
                    WaitUtils.waitForElementVisible(dateTimeHeader).getText();

            if (header.contains(expectedMonth) && header.contains(expectedYear)) {
                return;
            }

            WaitUtils.waitForElementClickable(nextMonthButton).click();
        }

        throw new RuntimeException(
                "Unable to reach month/year: " + expectedMonth + " " + expectedYear
        );
    }

    /* ---------------- HELPERS ---------------- */

    private void selectMonth(String month) {
        new Select(
                WaitUtils.waitForElementVisible(monthDropdown)
        ).selectByVisibleText(month);
    }

    private void selectYear(String year) {
        new Select(
                WaitUtils.waitForElementVisible(yearDropdown)
        ).selectByVisibleText(year);
    }

    private void selectDay(String day) {
        By dayBy = By.xpath(String.format(dayLocator, day));
        WaitUtils.waitForElementClickable(dayBy).click();
    }

    private void selectTime(String time) {

        WebElement timeElement =
                DriverManager.getDriver()
                        .findElements(timeListItem)
                        .stream()
                        .filter(e -> e.getText().equals(time))
                        .findFirst()
                        .orElseThrow(() ->
                                new RuntimeException("Time not found: " + time)
                        );

        WaitUtils.scrollIntoView(timeListItem);
        WaitUtils.safeClick(By.xpath(".//*[text()='" + time + "']"));

        // ✅ Wait until value reflects selected time
        WaitUtils.waitForCondition(() ->
                getSelectedDateTime().contains(time)
        );
    }

    /* ---------------- GETTERS ---------------- */

    public String getSelectedDate() {
        return DriverManager.getDriver()
                .findElement(selectDateInput)
                .getAttribute("value");
    }

    public String getSelectedDateTime() {
        return DriverManager.getDriver()
                .findElement(dateTimeInput)
                .getAttribute("value");
    }
}
