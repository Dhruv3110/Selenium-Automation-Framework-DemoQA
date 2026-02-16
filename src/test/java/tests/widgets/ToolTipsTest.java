package tests.widgets;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import pages.widgets.ToolTipsPage;
import pages.widgets.WidgetsPage;
import utils.Log;

public class ToolTipsTest extends BaseTest {

	@Test(description = "TC_TT_001 - Verify Tool Tips page loaded")
	public void verifyToolTipsPageLoaded() {

		Log.info("Starting TC_TT_001");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickToolTips();

		ToolTipsPage page = new ToolTipsPage();
		Assert.assertTrue(page.isToolTipsPageLoaded());

		Log.info("TC_TT_001 Passed");
	}

	@Test(description = "TC_TT_002 - Verify Button tooltip")
	public void verifyButtonTooltip() {

		Log.info("Starting TC_TT_002");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickToolTips();

		ToolTipsPage page = new ToolTipsPage();

		String tooltipText = page.getButtonTooltipText();
		Log.info("Button tooltip text: " + tooltipText);

		Assert.assertEquals(tooltipText, "You hovered over the Button", "Incorrect tooltip text for button");

		Log.info("TC_TT_002 Passed");
	}

	@Test(description = "TC_TT_003 - Verify Text Field tooltip")
	public void verifyTextFieldTooltip() {

		Log.info("Starting TC_TT_003");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickToolTips();

		ToolTipsPage page = new ToolTipsPage();

		String tooltipText = page.getTextFieldTooltipText();
		Log.info("Text Field tooltip text: " + tooltipText);

		Assert.assertEquals(tooltipText, "You hovered over the text field", "Incorrect tooltip text for text field");

		Log.info("TC_TT_003 Passed");
	}

	@Test(description = "TC_TT_004 - Verify Contrary tooltip")
	public void verifyContraryTooltip() {

		Log.info("Starting TC_TT_004");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickToolTips();

		ToolTipsPage page = new ToolTipsPage();

		String tooltipText = page.getContraryTooltipText();
		Log.info("Contrary tooltip text: " + tooltipText);

		Assert.assertEquals(tooltipText, "You hovered over the Contrary", "Incorrect tooltip text for Contrary");

		Log.info("TC_TT_004 Passed");
	}

	@Test(description = "TC_TT_005 - Verify Number tooltip")
	public void verifyNumberTooltip() {

		Log.info("Starting TC_TT_005");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickToolTips();

		ToolTipsPage page = new ToolTipsPage();

		String tooltipText = page.getNumberTooltipText();
		Log.info("Number tooltip text: " + tooltipText);

		Assert.assertEquals(tooltipText, "You hovered over the 1.10.32", "Incorrect tooltip text for number");

		Log.info("TC_TT_005 Passed");
	}
}
