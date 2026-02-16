package tests.widgets;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.homepage.HomePage;
import pages.widgets.SliderPage;
import pages.widgets.WidgetsPage;
import utils.Log;

public class SliderTest extends BaseTest {

    @Test(description = "TC_SL_001 - Verify Slider page loaded")
    public void verifySliderPageLoaded() {

        Log.info("Starting TC_SL_001");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickSlider();

        SliderPage page = new SliderPage();
        Assert.assertTrue(page.isSliderPageLoaded());

        Log.info("TC_SL_001 Passed");
    }

    @Test(description = "TC_SL_002 - Verify default slider value")
    public void verifyDefaultSliderValue() {

        Log.info("Starting TC_SL_002");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickSlider();

        SliderPage page = new SliderPage();

        int defaultValue = page.getSliderValue();
        Log.info("Default slider value: " + defaultValue);

        Assert.assertEquals(defaultValue, 25, "Default slider value mismatch");

        Log.info("TC_SL_002 Passed");
    }

    @Test(description = "TC_SL_003 - Move slider to specific value")
    public void moveSliderToSpecificValue() {

        Log.info("Starting TC_SL_003");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickSlider();

        SliderPage page = new SliderPage();

        page.setSliderValue(60);

        Assert.assertEquals(
                page.getSliderValue(),
                60,
                "Slider value not set correctly"
        );

        Log.info("TC_SL_003 Passed");
    }

    @Test(description = "TC_SL_004 - Move slider to max value")
    public void moveSliderToMaxValue() {

        Log.info("Starting TC_SL_004");

        new HomePage().clickWidgetsCard();
        new WidgetsPage().clickSlider();

        SliderPage page = new SliderPage();

        page.setSliderValue(100);

        Assert.assertEquals(
                page.getSliderValue(),
                100,
                "Slider max value not set correctly"
        );

        Log.info("TC_SL_004 Passed");
    }
}
