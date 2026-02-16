package tests.elements;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.elements.CheckBoxPage;
import pages.elements.ElementsPage;
import pages.homepage.HomePage;
import utils.Log;

public class CheckBoxTest extends BaseTest {

	@Test(description = "TC_CHK_001 - Verify navigation to Check Box page")
	public void verifyCheckBoxNavigation() {

		Log.info("Starting TC_CHK_001");
		new HomePage().clickElementsCard();
		new ElementsPage().clickCheckBox();

		CheckBoxPage checkBoxPage = new CheckBoxPage();
		Assert.assertTrue(checkBoxPage.isCheckBoxPageLoaded(), "Check Box page is not loaded");

		Log.info("TC_CHK_001 Passed");

	}

	@Test(description = "TC_CHK_002 - Verify Home selects all nodes")
	public void verifyHomeSelectsAll() {
		Log.info("Starting TC_CHK_002");

		new HomePage().clickElementsCard();
		new ElementsPage().clickCheckBox();

		CheckBoxPage checkBoxPage = new CheckBoxPage();
		checkBoxPage.selectHome();

		Assert.assertTrue(checkBoxPage.isHomeHalfChecked() == false, "Home should be fully checked");
		Assert.assertTrue(checkBoxPage.isDesktopChecked(), "Desktop should be checked");
		Assert.assertTrue(checkBoxPage.isNotesChecked(), "Notes should be checked");


		Log.info("TC_CHK_002 Passed");
	}

	@Test(description = "TC_CHK_003 - Verify Desktop selection behavior")
	public void verifyDesktopSelectionBehavior() {

		Log.info("Starting TC_CHK_003 - Desktop selection behavior");

		new HomePage().clickElementsCard();
		new ElementsPage().clickCheckBox();

		CheckBoxPage checkBoxPage = new CheckBoxPage();
		checkBoxPage.selectDesktop();

		Assert.assertTrue(checkBoxPage.isDesktopChecked(), "Desktop checkbox is not checked");
		Assert.assertTrue(checkBoxPage.isNotesChecked(), "Notes checkbox is not checked when Desktop is selected");
		Assert.assertTrue(checkBoxPage.isHomeHalfChecked(),
				"Home checkbox is not partially checked when only Desktop is selected");

		Log.info("TC_CHK_003 Passed");
	}

	@Test(description = "TC_CHK_004 - Verify parent and grandparent become partial when child is selected")
	public void verifyPartialSelection() {

		Log.info("Starting TC_CHK_004");

		new HomePage().clickElementsCard();
		new ElementsPage().clickCheckBox();

		CheckBoxPage checkBoxPage = new CheckBoxPage();
//		checkBoxPage.expandAll();
		checkBoxPage.selectNotesOnly();

		Assert.assertTrue(checkBoxPage.isDesktopHalfChecked(), "Desktop checkbox is not in partial (half-check) state");
		Assert.assertTrue(checkBoxPage.isHomeHalfChecked(), "Home checkbox is not in partial (half-check) state");

		Log.info("TC_CHK_004 Passed");

	}

	@Test(description = "TC_CHK_005 - Verify result panel updates correctly")
	public void verifyResultPanel() {

		Log.info("Starting TC_CHK_005");

		new HomePage().clickElementsCard();
		new ElementsPage().clickCheckBox();

		CheckBoxPage checkBoxPage = new CheckBoxPage();
//		checkBoxPage.expandAll();
		checkBoxPage.selectNotesOnly();

		Assert.assertTrue(checkBoxPage.isResultContaining("notes"),
				"Result panel did not update correctly for Notes selection");

		Log.info("TC_CHK_005 Passed");
	}

	@Test(description = "TC_CHK_006 - Verify multiple leaf node selection with hierarchy validation")
	public void verifyMultipleLeafSelection() {

		Log.info("Starting TC_CHK_006");

		new HomePage().clickElementsCard();
		new ElementsPage().clickCheckBox();

		CheckBoxPage checkBoxPage = new CheckBoxPage();
		checkBoxPage.selectMultipleLeafNodes();

		Assert.assertTrue(checkBoxPage.isReactChecked(), "React is not checked");
		
		Assert.assertTrue(checkBoxPage.isAngularChecked(), "Angular is not checked");

		Assert.assertTrue(checkBoxPage.isWorkspaceHalfChecked(), "Workspace should be partially checked");
		Assert.assertTrue(checkBoxPage.isDocumentsHalfChecked(), "Documents should be partially checked");
		Assert.assertTrue(checkBoxPage.isHomeHalfChecked(), "Home should be partially checked");

		Assert.assertTrue(checkBoxPage.isResultContaining("react"), "Result does not contain 'react'");
		Assert.assertTrue(checkBoxPage.isResultContaining("angular"), "Result does not contain 'angular'");

		Log.info("TC_CHK_006 Passed");
	}

}
