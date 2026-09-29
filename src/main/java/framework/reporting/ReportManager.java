package framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

    private static final ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static {
        extentReports = new ExtentReports();

        ExtentSparkReporter reporter = new ExtentSparkReporter("target/reports/ExtentReport.html");
        extentReports.attachReporter(reporter);
    }

    private ReportManager() { //to prevent instantiation
    }

    public static void createTest(String testName) {
        ReportManager.extentTest.set(extentReports.createTest(testName));
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void flush() {
            try{
                extentReports.flush();
            } catch (Exception e) {
                System.err.println("[WARN] Failed to flush Extent Report" + e.getMessage());
            }
    }

    public static void unload() { extentTest.remove(); }
}
