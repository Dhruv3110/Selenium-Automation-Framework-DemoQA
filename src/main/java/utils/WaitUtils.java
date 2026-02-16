package utils;

import java.time.Duration;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class WaitUtils {

    private WaitUtils() {
        // Utility class
    }

    /* ------------------------- CORE WAIT ------------------------- */

    private static WebDriverWait getWait() {
        WebDriverWait wait = WaitManager.getWait();
        if (wait == null) {
            throw new IllegalStateException(
                "WebDriverWait not initialized. Call WaitManager.initWait(driver) first."
            );
        }
        return wait;
    }

    /* ------------------------- ELEMENT WAITS ------------------------- */

    public static WebElement waitForElementVisible(By locator) {
        return getWait().until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForElementPresent(By locator) {
        return getWait().until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public static WebElement waitForElementClickable(By locator) {
        return getWait().until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static WebElement waitForElementClickable(WebElement element) {
        return getWait().until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

    /* ------------------------- ALERT & PAGE WAITS ------------------------- */

    public static Alert waitForAlert() {
        return getWait().until(
                ExpectedConditions.alertIsPresent()
        );
    }
    
    public static Alert waitForAlertWithTimeout(int timeoutSeconds) {
        WebDriver driver = DriverManager.getDriver();
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.alertIsPresent());
    }

//    public static boolean isAlertPresent() {
//        try {
//            DriverManager.getDriver().switchTo().alert();
//            return true;
//        } catch (NoAlertPresentException e) {
//            return false;
//        }
//    }

    public static boolean waitForTitleContains(String titlePart) {
        return getWait().until(
                ExpectedConditions.titleContains(titlePart)
        );
    }

    /* ------------------------- ACTION HELPERS ------------------------- */

    public static void safeClick(By locator) {
        try {
            waitForElementClickable(locator).click();
        } catch (ElementClickInterceptedException e) {
            Log.warn("Element click intercepted, using JS click: " + locator);
            jsClick(locator);
        }
    }

    public static void jsClick(By locator) {
        WebDriver driver = DriverManager.getDriver();
        WebElement element = waitForElementPresent(locator);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click()", element);
    }

    public static void scrollIntoView(By locator) {
        WebDriver driver = DriverManager.getDriver();
        WebElement element = waitForElementVisible(locator);

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest'})",
                        element
                );
    }

    public static void selectByVisibleText(By locator, String visibleText) {
        new Select(waitForElementVisible(locator))
                .selectByVisibleText(visibleText);
    }

    /* ------------------------- CUSTOM WAITS ------------------------- */

    public static void waitForCondition(BooleanSupplier condition) {
        getWait().until(driver -> condition.getAsBoolean());
    }

    /* ------------------------- HARD WAIT (DISCOURAGED) ------------------------- */
//
//    public static void waitForMillis(long millis) {
//        Log.warn("Hard wait used: " + millis + "ms (avoid if possible)");
//        try {
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
}
