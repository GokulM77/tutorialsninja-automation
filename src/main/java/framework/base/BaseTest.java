package framework.base;

import framework.driver.BrowserType;
import framework.driver.DriverFactory;
import framework.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    // TODO: Phase 4 — replace with ConfigManager.getBrowser() once config layer exists
    private static final BrowserType DEFAULT_BROWSER = BrowserType.CHROME;
    // TODO: Phase 4 — replace with ConfigManager.getBaseUrl() once config layer exists
    private static final String BASE_URL = "https://tutorialsninja.com/demo/index.php?route=common/home";
    //TODO: Phase 16 — replace with retryAnalyser
    int maxAttempt = 2;

        @BeforeMethod
        public void setUp ()
        {   //TODO: Phase 16 — replace with retryAnalyser
            for(int attempt =1; attempt <=maxAttempt; attempt ++) {
               try{
                DriverManager.setDriver(DriverFactory.createDriver(DEFAULT_BROWSER));
                DriverManager.getDriver().get(BASE_URL);
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

                if (attempt == maxAttempt) {
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
