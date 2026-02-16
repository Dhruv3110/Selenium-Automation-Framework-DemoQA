package tests.forms;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.forms.FormsPage;
import pages.forms.PracticeFormPage;
import pages.homepage.HomePage;
import utils.Log;

public class PracticeFormTest extends BaseTest {

    @Test(description = "TC_PF_001 - Verify Practice Form page loaded")
    public void verifyPracticeFormPageLoaded() {

        Log.info("Starting TC_PF_001");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();
        Assert.assertTrue(
                form.isPracticeFormPageLoaded(),
                "Practice Form page not loaded"
        );

        Log.info("TC_PF_001 Passed");
    }

    @Test(description = "TC_PF_002 - Submit form with mandatory fields only")
    public void submitMandatoryFieldsOnly() {

        Log.info("Starting TC_PF_002");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();

        form.enterFirstName("Dhruv");
        form.enterLastName("Gupta");
        form.selectGender("male");
        form.enterMobile("9876543210");
        form.submitForm();

        Assert.assertTrue(
                form.isSubmissionSuccessful(),
                "Submission modal not displayed"
        );

        Log.info("TC_PF_002 Passed");
    }

    @Test(description = "TC_PF_003 - Submit form with all fields filled")
    public void submitAllFields() {

        Log.info("Starting TC_PF_003");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();

        form.enterFirstName("Dhruv");
        form.enterLastName("Gupta");
        form.enterUserEmail("dhruv@test.com");
        form.selectGender("male");
        form.enterMobile("9876543210");
        form.selectDateOfBirth("8", "February", "1998");
        form.addSubject("Maths");
        form.addSubject("Computer Science");
        form.selectHobby("sports");
        form.selectHobby("reading");
        form.uploadPicture("test-data/profile.jpeg");
        form.enterCurrentAddress("Lucknow, Uttar Pradesh");
        form.selectState("Uttar Pradesh");
        form.selectCity("Lucknow");
        form.submitForm();

        Assert.assertTrue(
                form.isSubmissionSuccessful(),
                "Form submission failed"
        );

        Log.info("TC_PF_003 Passed");
    }

    @Test(description = "TC_PF_004 - Verify gender radio button selection")
    public void verifyGenderSelection() {

        Log.info("Starting TC_PF_004");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();
        form.selectGender("female");

        Assert.assertTrue(
                form.isGenderSelected("female"),
                "Gender not selected"
        );

        Log.info("TC_PF_004 Passed");
    }

    @Test(description = "TC_PF_005 - Verify hobbies checkbox selection")
    public void verifyHobbiesSelection() {

        Log.info("Starting TC_PF_005");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();
        form.selectHobby("sports");
        form.selectHobby("music");

        Assert.assertTrue(
                form.isHobbySelected("sports"),
                "Sports hobby not selected"
        );

        Log.info("TC_PF_005 Passed");
    }

    @Test(description = "TC_PF_006 - Verify date picker selection")
    public void verifyDatePicker() {

        Log.info("Starting TC_PF_006");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();
        form.selectDateOfBirth("31", "October", "2003");

        Assert.assertEquals(
                form.getSelectedDOB(),
                "31 Oct 2003",
                "Date not selected properly"
        );

        Log.info("TC_PF_006 Passed");
    }

    @Test(description = "TC_PF_007 - Verify subject selection")
    public void verifySubjectSelection() {

        Log.info("Starting TC_PF_007");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();
        form.addSubject("Physics");

        Assert.assertTrue(
                form.isSubjectAdded("Physics"),
                "Subject not selected"
        );

        Log.info("TC_PF_007 Passed");
    }

    @Test(description = "TC_PF_008 - Verify state & city dependency")
    public void verifyStateCityDependency() {

        Log.info("Starting TC_PF_008");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();
        form.selectState("Uttar Pradesh");
        form.selectCity("Lucknow");

        Assert.assertTrue(
                form.isCitySelected("Lucknow"),
                "City not selected correctly"
        );

        Log.info("TC_PF_008 Passed");
    }

    @Test(description = "TC_PF_009 - Verify file upload")
    public void verifyFileUpload() {

        Log.info("Starting TC_PF_009");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();

        // ✅ Mandatory fields
        form.enterFirstName("Dhruv");
        form.enterLastName("Gupta");
        form.selectGender("male");
        form.enterMobile("9876543210");

        // File upload
        form.uploadPicture("test-data/profile.jpeg");

        // Submit
        form.submitForm();

        // Assert from modal
        Assert.assertTrue(
                form.isFileUploaded("profile.jpeg"),
                "Uploaded file not shown in submission modal"
        );

        Log.info("TC_PF_009 Passed");
    }


    @Test(description = "TC_PF_010 - Verify form submission modal data")
    public void verifySubmissionModalData() {

        Log.info("Starting TC_PF_010");

        new HomePage().clickFormsCard();
        new FormsPage().clickPracticeForm();

        PracticeFormPage form = new PracticeFormPage();
        form.enterFirstName("Dhruv");
        form.enterLastName("Gupta");
        form.selectGender("male");
        form.enterMobile("9876543210");
        form.submitForm();

        Assert.assertEquals(
                form.getSubmittedStudentName(),
                "Dhruv Gupta"
        );

        Log.info("TC_PF_010 Passed");
    }
}
