package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitManager {

    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    private static final int DEFAULT_TIMEOUT = 60;

    private WaitManager() {
        // prevent object creation
    }
    public static void initWait(WebDriver driver) {
        wait.set(new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)));
    }
    public static WebDriverWait getWait() {
        return wait.get();
    }
    public static void removeWait() {
        wait.remove();
    }
}
