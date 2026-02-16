package tests.elements;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.elements.ButtonsPage;
import pages.elements.ElementsPage;
import pages.homepage.HomePage;
import utils.Log;

public class ButtonsTest extends BaseTest {

	@Test(description = "TC_BTN_001 - Verify Buttons Page Loaded")
	public void verifyButtonsPageLoaded() {
		Log.info("Starting TC_BTN_001");

		new HomePage().clickElementsCard();
		new ElementsPage().clickButtons();

		ButtonsPage buttonsPage = new ButtonsPage();

		Assert.assertTrue(buttonsPage.isButtonPageLoaded(), "Buttons Page not loaded");

		Log.info("TC_BTN_001 Passed");
	}

	@Test(description = "TC_BTN_002 - Verify Double Click Action")
	public void verifyDoubleClick() {
		Log.info("Starting TC_BTN_002");

		new HomePage().clickElementsCard();
		new ElementsPage().clickButtons();

		ButtonsPage buttonsPage = new ButtonsPage();

		buttonsPage.doubleClickButton();

		if (buttonsPage.isDoubleClickMessageDisplayed()) {
			Log.info("Double click message displayed successfully");
		} else {
			Log.warn("Doubleclick message not displayed(DemoQA instability)");
		}
		Assert.assertTrue(buttonsPage.isDoubleClickMessageDisplayed(), "Double click message was not displayed");

		Log.info("TC_BTN_002 Passed");
	}

	@Test(description = "TC_BTN_003 - Verify right click action")
	public void verifyRightClick() {

		Log.info("Starting TC_BTN_003");

		new HomePage().clickElementsCard();
		new ElementsPage().clickButtons();

		ButtonsPage buttonsPage = new ButtonsPage();
		buttonsPage.rightClickButton();

		Assert.assertTrue(buttonsPage.isRightClickMessageDisplayed(), "Right click message not displayed");

		Log.info("TC_BTN_003 Passed");
	}

	@Test(description = "TC_BTN_004 - Verify dynamic click action")
	public void verifyDynamicClick() {

		Log.info("Starting TC_BTN_004");

		new HomePage().clickElementsCard();
		new ElementsPage().clickButtons();

		ButtonsPage buttonsPage = new ButtonsPage();
		buttonsPage.clickDynamicButton();

		Assert.assertTrue(buttonsPage.isDynamicClickMessageDisplayed(), "Dynamic click message not displayed");

		Log.info("TC_BTN_004 Passed");
	}
}
