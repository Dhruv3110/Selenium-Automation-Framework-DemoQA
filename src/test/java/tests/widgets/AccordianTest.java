package tests.widgets;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import pages.widgets.AccordianPage;
import pages.widgets.WidgetsPage;
import utils.Log;

public class AccordianTest extends BaseTest {

	@Test(description = "TC_ACC_001 - Verify Accordian page loaded")
	public void verifyAccordianPageLoaded() {

		Log.info("Starting TC_ACC_001");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAccordian();

		AccordianPage accordianPage = new AccordianPage();

		Assert.assertTrue(accordianPage.isAccordianPageLoaded(), "Accordian page is not loaded");

		Log.info("TC_ACC_001 Passed");
	}

	@Test(description = "TC_ACC_002 - Verify Section 1 Expanded By Default")
	public void verifySection1ExpandedByDefault() {

		Log.info("Starting TC_ACC_002");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAccordian();

		AccordianPage accordianPage = new AccordianPage();
		Assert.assertTrue(accordianPage.isSection1Expanded(), "Section 1 should be expanded by default");

		Log.info("TC_ACC_002 Passed");
	}

	@Test(description = "TC_ACC_003 - Expand Section 2")
	public void verifySection2Expanded() {

		Log.info("Starting TC_ACC_003");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAccordian();

		AccordianPage accordianPage = new AccordianPage();
		accordianPage.expandSection2();

		Assert.assertTrue(accordianPage.isSection2Expanded(), "Section 2 should be expanded");

		Log.info("TC_ACC_003 Passed");
	}

	@Test(description = "TC_ACC_004 - Expand Section 3")
	public void verifySection3Expanded() {

		Log.info("Starting TC_ACC_004");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAccordian();

		AccordianPage accordianPage = new AccordianPage();
		accordianPage.expandSection3();

		Assert.assertTrue(accordianPage.isSection3Expanded(), "Section 3 should be expanded");

		Log.info("TC_ACC_004 Passed");
	}

	@Test(description = "TC_ACC_005 - Verify only one section expanded at a time")
	public void verifyOnlyOneSectionExpanded() {

		Log.info("Starting TC_ACC_005");

		new HomePage().clickWidgetsCard();
		new WidgetsPage().clickAccordian();

		AccordianPage accordianPage = new AccordianPage();

		accordianPage.expandSection2();
		Assert.assertTrue(accordianPage.isSection2Expanded());
		Assert.assertFalse(accordianPage.isSection3Expanded());

		accordianPage.expandSection3();
		Assert.assertTrue(accordianPage.isSection3Expanded());
		Assert.assertFalse(accordianPage.isSection2Expanded());

		Log.info("TC_ACC_005 Passed");
	}

}
