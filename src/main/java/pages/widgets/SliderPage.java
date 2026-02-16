package pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import utils.DriverManager;
import utils.Log;
import utils.WaitUtils;

public class SliderPage {

	private By headerText = By.xpath("//h1[text()='Slider']");
	private By sliderInput = By.xpath("//input[@type='range']");
	private By sliderValue = By.id("sliderValue");

	public boolean isSliderPageLoaded() {
		return WaitUtils.waitForElementVisible(headerText).isDisplayed();
	}

	public void setSliderValue(int value) {
		Log.info("Setting slider value to: " + value);

		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

		js.executeScript("const slider = arguments[0];" + "const value = arguments[1];" +

		// Set value using native setter
				"const nativeSetter = Object.getOwnPropertyDescriptor("
				+ "  window.HTMLInputElement.prototype, 'value').set;" + "nativeSetter.call(slider, value);" +

				// Fire BOTH events (this is critical)
				"slider.dispatchEvent(new Event('input', { bubbles: true }));"
				+ "slider.dispatchEvent(new Event('change', { bubbles: true }));",
				WaitUtils.waitForElementVisible(sliderInput), value);

		// Wait until UI reflects the value
		WaitUtils.waitForCondition(() -> getSliderValue() == value);

	}

	public int getSliderValue() {
		return Integer.parseInt(WaitUtils.waitForElementVisible(sliderValue).getAttribute("value"));
	}
}
