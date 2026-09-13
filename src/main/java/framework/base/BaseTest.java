package framework.base;

import framework.driver.BrowserType;
import framework.driver.DriverFactory;
import framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    // TODO: Phase 4 — replace with ConfigManager.getBrowser() once config layer exists
    private static final BrowserType DEFAULT_BROWSER = BrowserType.CHROME;

    @BeforeMethod
    public void setUp()
    {
        DriverManager.setDriver(DriverFactory.createDriver(DEFAULT_BROWSER));
    }

    @AfterMethod
    public void tearDown()
    {
        DriverManager.removeDriver();
    }
}
