package base;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.MediaEntityBuilder;

import utils.DriverManager;
import utils.ExtentReportManager;
import utils.WaitManager;

public abstract class BaseTest {

    private static final boolean HEADLESS = true;

    /* ----------- REPORT LIFECYCLE ----------- */

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        ExtentReportManager.initReport();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentReportManager.flushReport();
    }

    /* ----------- DRIVER + TEST NODE ----------- */

    @BeforeMethod(alwaysRun = true)
    public void setup(ITestResult result) {

        ExtentReportManager.createTest(result.getMethod().getMethodName());

        String browser = System.getProperty("browser", "chrome").toLowerCase();

        WebDriver driver;

        switch (browser) {

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (HEADLESS) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--width=1920");
                    firefoxOptions.addArguments("--height=1080");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (HEADLESS) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--window-size=1920,1080");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (HEADLESS) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--no-sandbox");
                }
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-gpu");

                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        DriverManager.setDriver(driver);
        WaitManager.initWait(driver);

        driver.get("https://demoqa.com");

        // Remove ads (ToolsQA specific)
        ((JavascriptExecutor) driver)
                .executeScript(
                        "var ads = document.getElementById('fixedban'); if (ads) ads.remove();"
                );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        WebDriver driver = DriverManager.getDriver();

        try {
            if (result.getStatus() == ITestResult.FAILURE) {

                String screenshot =
                        ExtentReportManager.captureScreenshot(driver, result.getMethod().getMethodName());

                ExtentReportManager.getTest().fail(
                        result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build()
                );

            } else if (result.getStatus() == ITestResult.SUCCESS) {
                ExtentReportManager.getTest().pass("Test passed");
            }

        } finally {

            if (driver != null) {
                driver.quit();
            }

            DriverManager.removeDriver();
            WaitManager.removeWait();
            ExtentReportManager.removeTest();
        }
    }
}
