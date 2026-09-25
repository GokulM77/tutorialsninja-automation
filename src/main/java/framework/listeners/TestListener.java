package framework.listeners;

import java.io.IOException;
import java.nio.file.Files;
import framework.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            //To check which thread screenshot is picked up during parallel run
            System.out.println("[FAILURE] " + result.getMethod().getMethodName() + " on thread " + Thread.currentThread().threadId());

            File screenshot = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("./target/screenshots/" + result.getMethod().getMethodName() + result.getStartMillis() + ".png");

            if (targetFile.getParentFile() != null) {
                Files.createDirectories(targetFile.getParentFile().toPath());
            }

            Files.copy(screenshot.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            System.err.println("[WARN] Failed to capture failure screenshot for method " + result.getMethod().getMethodName() + " due to: " + e.getMessage());
        }
    }
}