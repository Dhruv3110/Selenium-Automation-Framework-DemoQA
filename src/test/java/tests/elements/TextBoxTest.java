package tests.elements;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.elements.ElementsPage;
import pages.elements.TextBoxPage;
import pages.homepage.HomePage;
import utils.Log;

public class TextBoxTest extends BaseTest {

	@Test(description = "TC_TXT_001 - Verify navigation to Text Box page")
	public void verifyTextBoxNavigation() {
		Log.info("Starting TC_TXT_001...");

		HomePage homePage = new HomePage();
		homePage.clickElementsCard();

		ElementsPage elementsPage = new ElementsPage();
		Assert.assertTrue(elementsPage.isElementsPageLoaded());

		elementsPage.clickTextBox();

		TextBoxPage textBoxPage = new TextBoxPage();
		Assert.assertTrue(textBoxPage.isTextBoxPageLoaded());

		Log.info("TC_TXT_001 Passed");
	}

	@Test(description = "TC_TXT_002 - Submit Text Box form with valid data")
	public void submitTextBoxForm() {
		Log.info("Starting TC_TXT_002 - Submit Text Box with valid Data");

		HomePage homePage = new HomePage();
		homePage.clickElementsCard();

		ElementsPage elementsPage = new ElementsPage();
		elementsPage.clickTextBox();

		TextBoxPage textBoxPage = new TextBoxPage();

		textBoxPage.fillTextBoxForm("Dhruv Gupta", "dhruv@test.com", "Sikkim", "Delhi");
		textBoxPage.submitForm();

		Assert.assertTrue(textBoxPage.isOutputDisplayed(), "Output data not displayed after form submission");

		Log.info("TC_TXT_002 Passed");
	}
	
	@Test(description = "TC_TXT_003 - Invalid Email Validation")
	public void verifyInvalidEmailValidation() {
		Log.info("Starting TC_TXT_003 - Invalid Email Validation");

		HomePage homePage = new HomePage();
		homePage.clickElementsCard();

		ElementsPage elementsPage = new ElementsPage();
		elementsPage.clickTextBox();

		TextBoxPage textBoxPage = new TextBoxPage();

		textBoxPage.fillTextBoxForm("Dhruv Gupta", "abc@", "Sikkim", "Delhi");

		textBoxPage.submitForm();
		
	    Log.info("Verifying email field error state");
	    Assert.assertTrue(
	            textBoxPage.isEmailFieldInErrorState(),
	            "Email field did not show validation error for invalid email"
	    );

	    Log.info("TC_TXT_003 Passed");

	}
}
