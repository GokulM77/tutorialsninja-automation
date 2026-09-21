package framework.base;

import framework.config.ConfigManager;
import framework.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected void click(By locator)
    {
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text)
    {
        waitForVisible(locator).sendKeys(text);
    }

    protected String getText(By locator) { return waitForVisible(locator).getText(); }

    protected boolean isDisplayed(By locator)
    {
        return waitForVisible(locator).isDisplayed();
    }

    protected WebElement waitForVisible(By locator) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigManager.getTimeout()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigManager.getTimeout()))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }
}
