package tests.elements;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.elements.ElementsPage;
import pages.elements.RadioButtonPage;
import pages.homepage.HomePage;
import utils.Log;

public class RadioButtonTest extends BaseTest {
	@Test(description = "TC_RAD_001 - Verify navigation to Radio Button page")
	public void verifyRadioButtonNavigation() {

		Log.info("Starting TC_RAD_001");

		new HomePage().clickElementsCard();
		new ElementsPage().clickRadioButton();

		RadioButtonPage radioPage = new RadioButtonPage();
		Assert.assertTrue(radioPage.isRadioButtonPageLoaded(), "Radio Button page not loaded");

		Log.info("TC_RAD_001 Passed");
	}

	@Test(description = "TC_RAD_002 - Verify Yes radio button selection")
	public void verifyYesRadioSelection() {

		Log.info("Starting TC_RAD_002");

		new HomePage().clickElementsCard();
		new ElementsPage().clickRadioButton();

		RadioButtonPage radioPage = new RadioButtonPage();
		radioPage.selectYes();

		Assert.assertTrue(radioPage.isResultDisplayed("Yes"), "Yes selection result not displayed");

		Log.info("TC_RAD_002 Passed");
	}

	@Test(description = "TC_RAD_003 - Verify Impressive radio button selection")
	public void verifyImpressiveRadioSelection() {

		Log.info("Starting TC_RAD_003");

		new HomePage().clickElementsCard();
		new ElementsPage().clickRadioButton();

		RadioButtonPage radioPage = new RadioButtonPage();
		radioPage.selectImpressive();

		Assert.assertTrue(radioPage.isResultDisplayed("Impressive"), "Impressive selection result not displayed");

		Log.info("TC_RAD_003 Passed");
	}

	@Test(description = "TC_RAD_004 - Verify No radio button is disabled")
	public void verifyNoRadioDisabled() {

		Log.info("Starting TC_RAD_004");

		new HomePage().clickElementsCard();
		new ElementsPage().clickRadioButton();

		RadioButtonPage radioPage = new RadioButtonPage();

		Assert.assertTrue(radioPage.isNoRadioDisabled(), "'No' radio button is enabled but should be disabled");

		Log.info("TC_RAD_004 Passed");
	}

	@Test(description = "TC_RAD_005 - Verify switching radio button selection")
	public void verifyRadioSwitching() {

		Log.info("Starting TC_RAD_005");

		new HomePage().clickElementsCard();
		new ElementsPage().clickRadioButton();

		RadioButtonPage radioPage = new RadioButtonPage();
		radioPage.selectYes();
		radioPage.selectImpressive();

		Assert.assertTrue(radioPage.isResultDisplayed("Impressive"), "Radio selection did not switch correctly");

		Log.info("TC_RAD_005 Passed");
	}
}
