package pages.elements;

//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class WebTablesPage {

    /* ---------------- LOCATORS ---------------- */

    private By headerText = By.xpath("//h1[text()='Web Tables']");
    private By addButton = By.id("addNewRecordButton");

    // Registration form
    private By firstNameInput = By.id("firstName");
    private By lastNameInput = By.id("lastName");
    private By emailInput = By.id("userEmail");
    private By ageInput = By.id("age");
    private By salaryInput = By.id("salary");
    private By departmentInput = By.id("department");
    private By submitButton = By.id("submit");

    // Search
    private By searchBox = By.id("searchBox");

    // Table
    private By table = By.xpath("//table");
    private By tableRows = By.xpath("//table/tbody/tr");

    /* ---------------- DYNAMIC ROW LOCATORS ---------------- */

    private By rowByEmail(String email) {
        return By.xpath("//table//tr[td[text()='" + email + "']]");
    }

    private By editButtonByEmail(String email) {
        return By.xpath("//table//tr[td[text()='" + email + "']]//span[@title='Edit']");
    }

    private By deleteButtonByEmail(String email) {
        return By.xpath("//table//tr[td[text()='" + email + "']]//span[@title='Delete']");
    }

    /* ---------------- PAGE STATE ---------------- */

    public boolean isWebTablesPageLoaded() {
        return WaitUtils.waitForElementVisible(headerText).isDisplayed();
    }

    /* ---------------- ACTIONS ---------------- */

    public void clickAddButton() {
        Log.info("Clicking Add button");
        WaitUtils.waitForElementClickable(addButton).click();
    }

    public void fillRegistrationForm(
            String firstName,
            String lastName,
            String email,
            String age,
            String salary,
            String department) {

        Log.info("Filling Registration Form");

        WaitUtils.waitForElementVisible(firstNameInput).clear();
        DriverManager.getDriver().findElement(firstNameInput).sendKeys(firstName);

        WaitUtils.waitForElementVisible(lastNameInput).clear();
        DriverManager.getDriver().findElement(lastNameInput).sendKeys(lastName);

        WebElement emailEl = WaitUtils.waitForElementVisible(emailInput);
        if (emailEl.isEnabled()) {
            emailEl.clear();
            emailEl.sendKeys(email);
        }

        WaitUtils.waitForElementVisible(ageInput).clear();
        DriverManager.getDriver().findElement(ageInput).sendKeys(age);

        WaitUtils.waitForElementVisible(salaryInput).clear();
        DriverManager.getDriver().findElement(salaryInput).sendKeys(salary);

        WaitUtils.waitForElementVisible(departmentInput).clear();
        DriverManager.getDriver().findElement(departmentInput).sendKeys(department);
    }

    public void submitForm() {
        Log.info("Submitting Registration Form");
        WaitUtils.waitForElementClickable(submitButton).click();
        WaitUtils.waitForElementVisible(table);
    }

    public void editRecordByEmail(String email) {
        Log.info("Editing record with email: " + email);
        WaitUtils.waitForElementVisible(editButtonByEmail(email));
        WaitUtils.scrollIntoView(editButtonByEmail(email));
        WaitUtils.safeClick(editButtonByEmail(email));
    }

    public void deleteRecordByEmail(String email) {
        Log.info("Deleting record with email: " + email);
        WaitUtils.waitForElementVisible(deleteButtonByEmail(email));
        WaitUtils.scrollIntoView(deleteButtonByEmail(email));
        WaitUtils.safeClick(deleteButtonByEmail(email));
        WaitUtils.waitForElementVisible(table);
    }

    public void searchRecord(String keyword) {
        Log.info("Searching record: " + keyword);

        WebElement search = WaitUtils.waitForElementVisible(searchBox);
        search.clear();
        search.sendKeys(keyword);

        WaitUtils.waitForElementVisible(table);
    }

    /* ---------------- TABLE VALIDATIONS ---------------- */

    public int getRowCount() {
        return DriverManager.getDriver().findElements(tableRows).size();
    }

    public boolean isRecordPresentByEmail(String email) {
        return DriverManager.getDriver()
                .findElements(By.xpath("//table//td[text()='" + email + "']"))
                .size() > 0;
    }

    public boolean isRecordPresent(String text) {
        return DriverManager.getDriver()
                .findElements(By.xpath("//table//tr[td[contains(text(),'" + text + "')]]"))
                .size() > 0;
    }
}
