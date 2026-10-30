package framework.listeners;

import java.lang.reflect.Parameter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import framework.reporting.ReportManager;
import framework.utils.ScreenshotUtils;
import framework.utils.SensitiveDataMasker;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private ExtentTest currentTest(ITestResult result) {
        ExtentTest test = ReportManager.getTest();
        if (test == null) { //skipped by a failed @BeforeMethod, onTestStart never fired
            ReportManager.createTest(result.getMethod().getMethodName());
            test = ReportManager.getTest();
        }
        return test;
    }

    private String displayName(ITestResult result) {
        String name = result.getMethod().getMethodName();
        Object[] params = result.getParameters();
        if (params.length == 0) {
            return name;
        }

        // Cucumber runner: the first parameter is the scenario. Matched by class name so
        // src/main never imports Cucumber.
        if (params[0] != null && params[0].getClass().getName().startsWith("io.cucumber.")) {
            return String.valueOf(params[0]).replaceAll("^\"|\"$", ""); // strip the surrounding quotes
        }

        Parameter[] declared = result.getMethod().getConstructorOrMethod().getMethod().getParameters();
        return name + " [" + SensitiveDataMasker.describe(declared, params) + "]";
    }

    @Override
    public void onTestStart(ITestResult result) {
        ReportManager.createTest(displayName(result));
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
        System.out.println("[FAILURE] " + result.getMethod().getMethodName()+ " on thread " + Thread.currentThread().threadId());

        //Cucumber's @After hook runs before this callback and has already captured, so check that first.
        String screenshotPath = ScreenshotUtils.consumeStoredPath();
        if (screenshotPath == null) {
            //TestNG native tests: the driver is still alive here (@AfterMethod hasn't run yet).
            String fileName = result.getMethod().getMethodName()
                    + Thread.currentThread().threadId() + result.getStartMillis() + ".png";
            screenshotPath = ScreenshotUtils.capture(fileName);
        }

        try {
            ExtentTest test = currentTest(result);
            if (screenshotPath != null) {
                test.fail(result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
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
            //Override plain fallback name with parameter masked name
            test.getModel().setName(displayName(result));
            if (result.wasRetried()) {
                test.skip("Attempt failed and was retried: " + result.getThrowable());
            }else if(result.getThrowable() != null) {
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