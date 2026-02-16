package pages.alerts_frame_windows;

import org.openqa.selenium.By;

import utils.Log;
import utils.WaitUtils;

public class BrowserWindowsPage {
	
	private By headerText = By.xpath("//h1[text()='Browser Windows']");
	
    private By newTabBtn = By.id("tabButton");
    private By newWindowBtn = By.id("windowButton");
    private By newWindowMsgBtn = By.id("messageWindowButton");
    
    private By newPageText = By.id("sampleHeading");
    
    public boolean isBrowserWindowsPageLoaded() {
    	return WaitUtils.waitForElementVisible(headerText).isDisplayed();
    }
    
    public void clickNewTab() {
        Log.info("Clicking New Tab button");
        WaitUtils.waitForElementClickable(newTabBtn).click();
    }

    public void clickNewWindow() {
        Log.info("Clicking New Window button");
        WaitUtils.waitForElementClickable( newWindowBtn).click();
    }

    public void clickNewWindowMessage() {
        Log.info("Clicking New Window Message button");

        WaitUtils.scrollIntoView( newWindowMsgBtn);
        WaitUtils.safeClick( newWindowMsgBtn);
    }

    
    public String getNewPageText() {
        return WaitUtils.waitForElementVisible(newPageText).getText();
    }

}
