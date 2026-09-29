package framework.listeners;

import java.lang.reflect.Parameter;
import java.nio.file.Files;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import framework.driver.DriverManager;
import framework.reporting.ReportManager;
import framework.utils.SensitiveDataMasker;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    private ExtentTest currentTest(ITestResult result) {
        ExtentTest test = ReportManager.getTest();
        if (test == null) { //skipped by a failed @BeforeMethod, onTestStart never fired
            ReportManager.createTest(result.getMethod().getMethodName());
            test = ReportManager.getTest();
        }
        return test;
    }

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName();
        Object[] params = result.getParameters();
        if (params.length > 0) {
            Parameter[] declared = result.getMethod().getConstructorOrMethod().getMethod().getParameters();
            name += " [" + SensitiveDataMasker.describe(declared, params) + "]";
        }
        ReportManager.createTest(name);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            currentTest(result).pass("Test passed");
        } finally {
            ReportManager.unload();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        //To check which thread screenshot is picked up during parallel run
        System.out.println("[FAILURE] " + result.getMethod().getMethodName() + " on thread " + Thread.currentThread().threadId());
        //common name used for both report and screenshot
        String screenshotFileName = result.getMethod().getMethodName() + result.getStartMillis() + ".png";
        // "../" to get into target directory
        String relativePathToScreenshot = "../screenshots/" + screenshotFileName;
        boolean screenshotAttached = false;

        try {
            File screenshot = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

            File targetFile = new File("./target/screenshots/" + screenshotFileName);
            Files.createDirectories(targetFile.getParentFile().toPath());
            Files.copy(screenshot.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            screenshotAttached = true;

        } catch (Exception e) {
            System.err.println("[WARN] Failed to capture screenshot " + screenshotFileName + " due to: " + e.getMessage());
        }

        try {
            ExtentTest test = currentTest(result);

            if (screenshotAttached) {
                //attaching screenshot to report
                test.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(relativePathToScreenshot).build());
            } else {
                test.fail(result.getThrowable());
                test.info("Note: Screenshot Unavailable.");
            }
        } finally {
            ReportManager.unload();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            ExtentTest test = currentTest(result);
            if(result.getThrowable() != null) {
                test.skip(result.getThrowable());
            } else {
                test.skip("Test Skipped");
            }
            } finally {
            ReportManager.unload();
        }
    }

    @Override
    public void onFinish(ITestContext context) { ReportManager.flush(); }
}