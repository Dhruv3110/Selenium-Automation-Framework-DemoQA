package utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public final class WindowUtils {

    private WindowUtils() {
        // Utility class
    }

    private static WebDriver getDriver() {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }
        return driver;
    }

    /* ---------------- WINDOW INFO ---------------- */

    public static String getCurrentWindow() {
        return getDriver().getWindowHandle();
    }

    public static int getWindowCount() {
        return getDriver().getWindowHandles().size();
    }

    /* ---------------- WINDOW SWITCH ---------------- */

    public static void switchToNewWindow(String parentWindow) {

        WaitUtils.waitForCondition(() ->
                getDriver().getWindowHandles().size() > 1
        );

        Set<String> windows = getDriver().getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                getDriver().switchTo().window(window);
                return;
            }
        }

        throw new RuntimeException("No new window found to switch");
    }

    public static void switchToParentWindow(String parentWindow) {
        getDriver().switchTo().window(parentWindow);
    }

    /* ---------------- WINDOW CLOSE ---------------- */

    public static void closeCurrentWindow() {
        getDriver().close();
    }

    public static void closeChildWindows(String parentWindow) {

        for (String window : getDriver().getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                getDriver().switchTo().window(window);
                getDriver().close();
            }
        }

        getDriver().switchTo().window(parentWindow);
    }
}
