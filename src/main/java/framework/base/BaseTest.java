package framework.base;

import framework.config.ConfigManager;
import framework.driver.BrowserType;
import framework.driver.DriverFactory;
import framework.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

        @BeforeMethod
        public void setUp ()
        {   //TODO: This try catch block is used to handle flaky browser initialisation using a retry logic
            for(int attempt =1; attempt <=ConfigManager.getMaxAttempt(); attempt ++) {
               try{
                DriverManager.setDriver(DriverFactory.createDriver(ConfigManager.getBrowser()));
                DriverManager.getDriver().get(ConfigManager.getBaseUrl());
                return;
        } catch (Exception e) {
                   System.out.println("Attempt " + attempt + " failed: " + e.getMessage());

                try {
                    if (DriverManager.getDriver() != null) {
                        DriverManager.getDriver().quit();
                    }
                } catch (Exception cleanupException) {
                    System.out.println("Cleanup failed or driver was already null: " + cleanupException.getMessage());
                } finally { DriverManager.removeDriver();
                }

                if (attempt == ConfigManager.getMaxAttempt()) {
                    throw e;
                }
            }
        }
    }

    @AfterMethod
    public void tearDown()
    {
        DriverManager.removeDriver();
    }
}
