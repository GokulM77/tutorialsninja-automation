package framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class DriverFactory {

    public static WebDriver createDriver(BrowserType browserType)
    {
        WebDriver driver;

        switch(browserType)
        {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Entered Browser not supported");
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        return driver;
    }
}
