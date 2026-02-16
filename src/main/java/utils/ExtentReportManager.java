package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private ExtentReportManager() {}

    public static synchronized void initReport() {
        if (extent == null) {
            String timestamp =
                    new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

            String reportPath =
                    System.getProperty("user.dir") +
                            "/reports/ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("DemoQA Automation Report");
            reporter.config().setDocumentTitle("Test Execution Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
    }

    // ✅ ONE NODE PER TEST METHOD
    public static void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void removeTest() {
        test.remove();
    }

    public static synchronized void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            String timestamp =
                    new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

            String path =
                    System.getProperty("user.dir") +
                            "/screenshots/" + testName + "_" + timestamp + ".png";

            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (Exception e) {
            return null;
        }
    }
}
