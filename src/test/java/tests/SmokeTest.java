package tests;

import framework.base.BaseTest;
import framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test
    public void getBrowser()
    {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
        Assert.assertNotNull(driver.getTitle());
    }
}
