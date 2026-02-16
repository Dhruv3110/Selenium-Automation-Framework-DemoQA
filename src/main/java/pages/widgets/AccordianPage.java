package pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class AccordianPage {

	/* ---------------- LOCATORS ---------------- */

	private By headerText = By.xpath("//h1[text()='Accordian']");

	private By section1Button = By.xpath("(//button[contains(@class,'accordion-button')])[1]");
	private By section2Button = By.xpath("(//button[contains(@class,'accordion-button')])[2]");
	private By section3Button = By.xpath("(//button[contains(@class,'accordion-button')])[3]");

	/* ---------------- PAGE STATE ---------------- */

	public boolean isAccordianPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	/* ---------------- EXPAND ACTIONS ---------------- */
	
    private boolean isExpanded(By sectionButton) {
        return "true".equals(
                WaitUtils.waitForElementPresent(sectionButton)
                        .getAttribute("aria-expanded")
        );
    }

	private void expand(By sectionButton) {
	    WebElement button = WaitUtils.waitForElementVisible(sectionButton);

	    if (!"true".equals(button.getAttribute("aria-expanded"))) {
	        WaitUtils.scrollIntoView(sectionButton);
	        WaitUtils.safeClick(sectionButton);
	    }

	    WaitUtils.waitForCondition(() ->
	            "true".equals(button.getAttribute("aria-expanded")));
	}


	/* ---------------- STATE CHECKS ---------------- */

    public void expandSection1() {
        expand(section1Button);
    }

    public void expandSection2() {
        expand(section2Button);
    }

    public void expandSection3() {
        expand(section3Button);
    }

    /* ---------------- STATE CHECKS ---------------- */

    public boolean isSection1Expanded() {
        return isExpanded(section1Button);
    }

    public boolean isSection2Expanded() {
        return isExpanded(section2Button);
    }

    public boolean isSection3Expanded() {
        return isExpanded(section3Button);
    }

}
