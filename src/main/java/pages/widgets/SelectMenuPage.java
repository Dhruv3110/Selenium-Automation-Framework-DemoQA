package pages.widgets;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.Log;
import utils.WaitUtils;

public class SelectMenuPage {

    /* ---------------- LOCATORS ---------------- */

    private By headerText = By.xpath("//h1[text()='Select Menu']");

    // React Select inputs (anchored to stable containers)
    private By selectValueInput =
            By.xpath("//div[@id='withOptGroup']//input");

    private By selectOneInput =
            By.xpath("//div[@id='selectOne']//input");

    private By multiSelectInput =
            By.xpath("//div[@id='selectMenuContainer']//input");

    // Containers used for validation (text-based)
    private By selectValueContainer = By.id("withOptGroup");
    private By selectOneContainer   = By.id("selectOne");
    private By multiSelectContainer = By.id("selectMenuContainer");

    // Old style selects
    private By oldStyleSelect = By.id("oldSelectMenu");
    private By standardMultiSelect = By.id("cars");

    /* ---------------- PAGE STATE ---------------- */

    public boolean isSelectMenuPageLoaded() {
        return WaitUtils.waitForElementVisible(headerText).isDisplayed();
    }

    /* ---------------- REACT SELECT ACTIONS ---------------- */

    public void selectValue(String value) {
        Log.info("Selecting value: " + value);

        WebElement input = WaitUtils.waitForElementVisible(selectValueInput);
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public void selectOne(String value) {
        Log.info("Selecting one: " + value);

        WebElement input = WaitUtils.waitForElementVisible(selectOneInput);
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public void selectMultiple(String value) {
        Log.info("Selecting multiple value: " + value);

        WebElement input = WaitUtils.waitForElementVisible(multiSelectInput);
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    /* ---------------- REACT SELECT ASSERTIONS ---------------- */

    public boolean isSingleValueSelected(String value) {
        return WaitUtils.waitForElementVisible(selectValueContainer)
                .getText()
                .contains(value);
    }

    public boolean isSelectOneValueSelected(String value) {
        return WaitUtils.waitForElementVisible(selectOneContainer)
                .getText()
                .contains(value);
    }

    public boolean isMultiValueSelected(String value) {
        return WaitUtils.waitForElementVisible(multiSelectContainer)
                .getText()
                .contains(value);
    }

    /* ---------------- OLD STYLE SELECT ---------------- */

    public void selectOldStyleValue(String value) {
        Log.info("Selecting old style value: " + value);

        Select select = new Select(
                WaitUtils.waitForElementVisible(oldStyleSelect)
        );
        select.selectByVisibleText(value);
    }

    public String getOldStyleSelectedValue() {
        Select select = new Select(
                WaitUtils.waitForElementVisible(oldStyleSelect)
        );
        return select.getFirstSelectedOption().getText();
    }

    /* ---------------- STANDARD MULTI SELECT ---------------- */

    public void selectMultipleCars(List<String> cars) {
        Log.info("Selecting multiple cars: " + cars);

        Select select = new Select(
                WaitUtils.waitForElementVisible(standardMultiSelect)
        );

        for (String car : cars) {
            select.selectByVisibleText(car);
        }
    }

    public List<WebElement> getSelectedCars() {
        Select select = new Select(
                WaitUtils.waitForElementVisible(standardMultiSelect)
        );
        return select.getAllSelectedOptions();
    }
}
