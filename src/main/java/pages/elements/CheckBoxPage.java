package pages.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class CheckBoxPage {

    /* ---------------- LOCATORS ---------------- */

    private By headerText = By.xpath("//h1[text()='Check Box']");
    private By switcher = By.xpath(".//span[contains(@class,'rc-tree-switcher')]");

    // Result panel
    private By resultBox = By.id("result");
    private By resultItems = By.cssSelector("#result span.text-success");

    /* ---------------- GENERIC CHECKBOX LOCATORS ---------------- */

    private By checkboxByLabel(String label) {
        return By.xpath(
            "//span[@role='checkbox' and contains(@aria-label,'Select') and contains(@aria-label,'" + label + "')]"
        );
    }


    /* ---------------- PAGE STATE ---------------- */

    public boolean isCheckBoxPageLoaded() {
        return WaitUtils.waitForElementVisible(headerText).isDisplayed();
    }

    /* ---------------- TREE ACTIONS ---------------- */

    /**
     * Expands a tree node only if it is collapsed.
     * Uses aria-expanded instead of UI buttons.
     */
    public void expandNodeIfCollapsed(String nodeName) {

        By nodeBy = By.xpath(
            "//div[@role='treeitem' and .//span[text()='" + nodeName + "']]"
        );

        WebElement node = WaitUtils.waitForElementPresent(nodeBy);

        if ("false".equals(node.getAttribute("aria-expanded"))) {

            node.findElement(switcher).click();

            // 🔴 Re-locate node inside wait (NO stale reference)
            WaitUtils.waitForCondition(() -> {
                WebElement refreshedNode =
                    DriverManager.getDriver().findElement(nodeBy);
                return "true".equals(refreshedNode.getAttribute("aria-expanded"));
            });
        }
    }


    
    private void ensurePathExpanded(String... nodes) {
        for (String node : nodes) {
            expandNodeIfCollapsed(node);
        }
    }
    




    /* ---------------- SELECTION ACTIONS ---------------- */

    public void selectHome() {
        Log.info("Selecting Home checkbox");
        WaitUtils.safeClick(checkboxByLabel("Home"));
    }

    public void selectDesktop() {
        Log.info("Selecting Desktop checkbox");
        expandNodeIfCollapsed("Home");
        clickCheckbox("Desktop");
    }

    public void selectNotesOnly() {
        Log.info("Selecting Notes checkbox only");
        expandNodeIfCollapsed("Home");
        expandNodeIfCollapsed("Desktop");
        WaitUtils.safeClick(checkboxByLabel("Notes"));
    }
    private void clickCheckbox(String label) {

        By checkbox = checkboxByLabel(label);

        WaitUtils.waitForCondition(() ->
            DriverManager.getDriver().findElements(checkbox).size() > 0
        );

        WaitUtils.safeClick(checkbox);
    }




    public void selectMultipleLeafNodes() {
        Log.info("Selecting multiple leaf nodes");

        expandNodeIfCollapsed("Home");
        expandNodeIfCollapsed("Documents");
        expandNodeIfCollapsed("WorkSpace");

        clickCheckbox("React");
        clickCheckbox("Angular");
    }



    /* ---------------- STATE CHECKS (ARIA-BASED) ---------------- */

    private boolean isCheckboxState(String label, String expectedState) {
        String actualState =
        WaitUtils.waitForElementPresent(checkboxByLabel(label))
                 .getAttribute("aria-checked");
        return expectedState.equals(actualState);
    }

    public boolean isHomeHalfChecked() {
        // Home is always visible, no expansion needed
        return isCheckboxState("Home", "mixed");
    }

    public boolean isDesktopChecked() {
        ensurePathExpanded("Home");
        return isCheckboxState("Desktop", "true");
    }

    public boolean isDesktopHalfChecked() {
        ensurePathExpanded("Home");
        return isCheckboxState("Desktop", "mixed");
    }

    public boolean isNotesChecked() {
        ensurePathExpanded("Home", "Desktop");
        return isCheckboxState("Notes", "true");
    }

    public boolean isCommandsChecked() {
        ensurePathExpanded("Home", "Desktop");
        return isCheckboxState("Commands", "true");
    }

    public boolean isReactChecked() {
        ensurePathExpanded("Home", "Documents", "WorkSpace");
        return isCheckboxState("React", "true");
    }

    public boolean isAngularChecked() {
        ensurePathExpanded("Home", "Documents", "WorkSpace");
        return isCheckboxState("Angular", "true");
    }

    public boolean isWorkspaceHalfChecked() {
        ensurePathExpanded("Home", "Documents");
        return isCheckboxState("WorkSpace", "mixed");
    }

    public boolean isDocumentsHalfChecked() {
        ensurePathExpanded("Home");
        return isCheckboxState("Documents", "mixed");
    }

    /* ---------------- RESULT PANEL ---------------- */

    public boolean isResultContaining(String value) {
        return WaitUtils.waitForElementVisible(resultBox)
                .getText()
                .toLowerCase()
                .contains(value.toLowerCase());
    }

    public List<WebElement> getResultItems() {
        return DriverManager.getDriver().findElements(resultItems);
    }
}
