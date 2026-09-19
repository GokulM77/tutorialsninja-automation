package framework.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() { //to prevent instantiation
    }

    public static void setDriver(WebDriver driver)
    {
        DriverManager.driver.set(driver);
    }
    public static WebDriver getDriver()
    {
        return driver.get();
    }

    public static void removeDriver() {
        if (driver.get() != null) {
            try {
                driver.get().quit();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        driver.remove();
    }
}
