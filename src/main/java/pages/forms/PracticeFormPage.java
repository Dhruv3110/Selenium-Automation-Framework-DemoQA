package pages.forms;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class PracticeFormPage {

	private By headerText = By.xpath("//h1[text()='Practice Form']");

	private By firstNameInput = By.id("firstName");
	private By lastNameInput = By.id("lastName");
	private By emailInput = By.id("userEmail");

	private By maleRadio = By.xpath("//label[text()='Male']");
	private By femaleRadio = By.xpath("//label[text()='Female']");
	private By otherRadio = By.xpath("//label[text()='Other']");

	private By mobileInput = By.id("userNumber");

	private By dobInput = By.id("dateOfBirthInput");
	private By monthSelect = By.className("react-datepicker__month-select");
	private By yearSelect = By.className("react-datepicker__year-select");

	private By subjectsInput = By.id("subjectsInput");

	private By sportsHobby = By.xpath("//label[text()='Sports']");
	private By readingHobby = By.xpath("//label[text()='Reading']");
	private By musicHobby = By.xpath("//label[text()='Music']");

	private By uploadPicture = By.id("uploadPicture");
	private By currentAddress = By.id("currentAddress");

	private By stateInput = By.id("react-select-3-input");
	private By cityInput = By.id("react-select-4-input");

	private By submitButton = By.id("submit");

	private By submissionModal = By.id("example-modal-sizes-title-lg");
	private By submittedStudentName = By.xpath("//td[text()='Student Name']/following-sibling::td");

	public boolean isPracticeFormPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public boolean isSubmissionSuccessful() {
		return WaitUtils.waitForElementVisible(submissionModal).isDisplayed();
	}

	public boolean isSubjectAdded(String subject) {
		return DriverManager.getDriver().getPageSource().contains(subject);
	}

	public boolean isCitySelected(String city) {
		return DriverManager.getDriver().getPageSource().contains(city);
	}

	public boolean isFileUploaded(String fileName) {

		WaitUtils.waitForElementVisible(submissionModal);

		By uploadedFileCell = By.xpath("//td[text()='Picture']/following-sibling::td");

		String uploadedFile = WaitUtils.waitForElementVisible(uploadedFileCell).getText();

		Log.info("Uploaded file shown in modal: " + uploadedFile);
		return uploadedFile.contains(fileName);
	}

	public String getSubmittedStudentName() {
		return WaitUtils.waitForElementVisible(submittedStudentName).getText();
	}

	public void enterFirstName(String firstName) {
		Log.info("Entering First Name: " + firstName);
		WaitUtils.waitForElementVisible(firstNameInput).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		Log.info("Entering Last Name: " + lastName);
		WaitUtils.waitForElementVisible(lastNameInput).sendKeys(lastName);
	}

	public void enterUserEmail(String email) {
		Log.info("Entering Email: " + email);
		WaitUtils.waitForElementVisible(emailInput).sendKeys(email);
	}

	public void selectGender(String gender) {
		Log.info("Selecting Gender: " + gender);

		By locator;
		switch (gender.toLowerCase()) {
		case "male":
			locator = maleRadio;
			break;
		case "female":
			locator = femaleRadio;
			break;
		case "other":
			locator = otherRadio;
			break;
		default:
			throw new IllegalArgumentException("Invalid gender: " + gender);
		}

		WaitUtils.scrollIntoView(locator);
		WaitUtils.jsClick(locator);
	}

	public boolean isGenderSelected(String gender) {
		String id = gender.equalsIgnoreCase("male") ? "1" : gender.equalsIgnoreCase("female") ? "2" : "3";

		return DriverManager.getDriver().findElement(By.id("gender-radio-" + id)).isSelected();
	}

	public void enterMobile(String mobile) {
		Log.info("Entering Mobile: " + mobile);

		WebElement mobileField = WaitUtils.waitForElementVisible(mobileInput);
		mobileField.clear();
		mobileField.sendKeys(mobile);
	}

	public void selectDateOfBirth(String day, String month, String year) {
		Log.info("Selecting DOB: " + day + "-" + month + "-" + year);

		WaitUtils.waitForElementClickable(dobInput).click();

		WaitUtils.selectByVisibleText(monthSelect, month);
		WaitUtils.selectByVisibleText(yearSelect, year);

		By dayLocator = By.xpath("//div[contains(@class,'react-datepicker__day') "
				+ "and not(contains(@class,'outside-month')) and text()='" + day + "']");

		WaitUtils.scrollIntoView(dayLocator);
		WaitUtils.safeClick(dayLocator);
	}

	public String getSelectedDOB() {
		return DriverManager.getDriver().findElement(dobInput).getAttribute("value");
	}

	public void addSubject(String subject) {
		Log.info("Adding Subject: " + subject);

		WebElement input = WaitUtils.waitForElementVisible(subjectsInput);

		input.sendKeys(subject);
		input.sendKeys(Keys.ENTER);
	}

	public void selectHobby(String hobby) {
		Log.info("Selecting Hobby: " + hobby);

		By locator;
		switch (hobby.toLowerCase()) {
		case "sports":
			locator = sportsHobby;
			break;
		case "reading":
			locator = readingHobby;
			break;
		case "music":
			locator = musicHobby;
			break;
		default:
			throw new IllegalArgumentException("Invalid hobby: " + hobby);
		}

		WaitUtils.scrollIntoView(locator);
		WaitUtils.safeClick(locator);
	}

	public boolean isHobbySelected(String hobby) {
		String id = hobby.equalsIgnoreCase("sports") ? "1" : hobby.equalsIgnoreCase("reading") ? "2" : "3";

		return DriverManager.getDriver().findElement(By.id("hobbies-checkbox-" + id)).isSelected();
	}

	public void uploadPicture(String filePath) {
		Log.info("Uploading picture: " + filePath);

		WebElement fileInput = WaitUtils.waitForElementPresent(uploadPicture);
		fileInput.sendKeys(new File(filePath).getAbsolutePath());
	}

	public void enterCurrentAddress(String address) {
		Log.info("Entering Current Address");
		WaitUtils.waitForElementVisible(currentAddress).sendKeys(address);
	}

	public void selectState(String state) {
		Log.info("Selecting State: " + state);

		WebElement stateField = WaitUtils.waitForElementVisible(stateInput);

		stateField.sendKeys(state);
		stateField.sendKeys(Keys.ENTER);
	}

	public void selectCity(String city) {
		Log.info("Selecting City: " + city);

		WebElement cityField = WaitUtils.waitForElementVisible(cityInput);

		cityField.sendKeys(city);
		cityField.sendKeys(Keys.ENTER);
	}

	public void submitForm() {

		Log.info("Submitting Practice Form");

		WebDriver driver = DriverManager.getDriver();

		((JavascriptExecutor) driver).executeScript(
				"document.querySelector('footer')?.remove();" + "document.querySelector('#fixedban')?.remove();");

		WebElement submit = WaitUtils.waitForElementPresent(submitButton);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", submit);

		((JavascriptExecutor) driver)
				.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles:true}));", submit);

		WaitUtils.waitForElementVisible(submissionModal);
	}

}
