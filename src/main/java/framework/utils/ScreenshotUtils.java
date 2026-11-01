package framework.utils;

import framework.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtils {

    private static final ThreadLocal<String> pendingPath = new ThreadLocal<>();

    private ScreenshotUtils() {}

    public static String capture(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File target = new File("./target/screenshots/" + fileName);
            Files.createDirectories(target.getParentFile().toPath());
            Files.copy(screenshot.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            // "../" to get into target directory
            return "../screenshots/" + fileName;
        }
        catch (Exception e) {
            System.err.println("[WARN] Failed to capture screenshot " + fileName + " due to: " + e.getMessage());
            return null;
        }
    }

    // Only the Cucumber hook calls this, because it captures before the listener fires.
    public static void storeForListener(String path) {
        if (path == null) pendingPath.remove();
        else pendingPath.set(path);
    }

    public static String consumeStoredPath() {
        String path = pendingPath.get();
        pendingPath.remove();
        return path;
    }
}