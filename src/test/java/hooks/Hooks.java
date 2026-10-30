package hooks;

import framework.config.ConfigManager;
import framework.driver.DriverFactory;
import framework.driver.DriverManager;
import framework.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {
        ScreenshotUtils.consumeStoredPath(); // discard anything left from an earlier scenario
        DriverManager.setDriver(DriverFactory.createDriver(ConfigManager.getBrowser()));
        DriverManager.getDriver().get(ConfigManager.getBaseUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                String fileName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_")
                        + Thread.currentThread().threadId() + System.currentTimeMillis() + ".png";
                ScreenshotUtils.storeForListener(ScreenshotUtils.capture(fileName));
            }
        }
        finally {
            DriverManager.removeDriver();
        }
    }
}
