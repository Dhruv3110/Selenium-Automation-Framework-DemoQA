package tests.elements;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.elements.ElementsPage;
import pages.homepage.HomePage;
import utils.Log;

public class ElementsTest extends BaseTest{
	
	@Test(description = "TC_ELE_001 - Verify navigation to Elements page")
	public void verifyElementsPageNavigation() {
		
		Log.info("TC_ELE_001 started - Verify Navigation to Elements Page");
		HomePage homePage = new HomePage();
		
		homePage.clickElementsCard();
		
		ElementsPage elementsPage = new ElementsPage();
		Assert.assertTrue(elementsPage.isElementsPageLoaded(), "Elements page was not Loaded");
		Log.info("TC_ELE_001 passed");
	}
	
}
