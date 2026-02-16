package tests.widgets;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import pages.widgets.AutoCompletePage;
import pages.widgets.WidgetsPage;
import utils.Log;

public class AutoCompleteTest extends BaseTest {

	@Test(description = "TC_AC_001 - Verify Auto Complete page loaded")
	public void verifyAutoCompletePageLoaded() {

		Log.info("Starting TC_AC_001 : Verify Auto Complete page loaded");

		Log.info("Navigating to Widgets section");
		new HomePage().clickWidgetsCard();

		Log.info("Navigating to Auto Complete page");
		new WidgetsPage().clickAutoComplete();

		AutoCompletePage page = new AutoCompletePage();

		Log.info("Verifying Auto Complete page is loaded");
		Assert.assertTrue(page.isAutoCompletePageLoaded(), "Auto Complete page did not load successfully");

		Log.info("TC_AC_001 Passed");
	}

	@Test(description = "TC_AC_002 - Select multiple colors")
	public void selectMultipleColors() {

		Log.info("Starting TC_AC_002 : Select multiple colors");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAutoComplete();

		AutoCompletePage page = new AutoCompletePage();

		Log.info("Selecting multiple colors: Red, Blue");
		page.selectMultipleColor("Red");
		page.selectMultipleColor("Blue");

		Log.info("Verifying selected colors");
		Assert.assertTrue(page.isMultiColorSelected("Red"), "Red color not selected");
		Assert.assertTrue(page.isMultiColorSelected("Blue"), "Blue color not selected");

		Log.info("TC_AC_002 Passed");
	}

	@Test(description = "TC_AC_003 - Remove one selected color")
	public void removeMultiColor() {

		Log.info("Starting TC_AC_003 : Remove one selected color");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAutoComplete();

		AutoCompletePage page = new AutoCompletePage();

		Log.info("Selecting color: Green");
		page.selectMultipleColor("Green");

		Assert.assertTrue(page.isMultiColorSelected("Green"), "Green color should be selected before removal");

		Log.info("Removing color: Green");
		page.removeMultiColor("Green");

		Log.info("Verifying Green color is removed");
		Assert.assertFalse(page.isMultiColorSelected("Green"), "Green color should be removed");

		Log.info("TC_AC_003 Passed");
	}

	@Test(description = "TC_AC_004 - Select single color")
	public void selectSingleColor() {

		Log.info("Starting TC_AC_004 : Select single color");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAutoComplete();

		AutoCompletePage page = new AutoCompletePage();

		Log.info("Selecting single color: Purple");
		page.selectSingleColor("Purple");

		Log.info("Verifying selected single color");
		Assert.assertTrue(page.isSingleColorSelected("Purple"), "Single color selection failed");

		Log.info("TC_AC_004 Passed");
	}

	@Test(description = "TC_AC_005 - Replace single color")
	public void replaceSingleColor() {

		Log.info("Starting TC_AC_005 : Replace single color");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAutoComplete();

		AutoCompletePage page = new AutoCompletePage();

		Log.info("Selecting initial single color: Black");
		page.selectSingleColor("Black");

		Log.info("Replacing single color with: White");
		page.selectSingleColor("White");

		Log.info("Verifying replaced color");
		Assert.assertTrue(page.isSingleColorSelected("White"), "Single color was not replaced correctly");

		Log.info("TC_AC_005 Passed");
	}
}
