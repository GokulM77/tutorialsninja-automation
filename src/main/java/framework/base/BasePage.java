package framework.base;

import framework.config.ConfigManager;
import framework.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

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

    protected void selectByVisibleText(By locator, String visibleText) {
        new Select(waitForVisible(locator)).selectByVisibleText(visibleText);
    }

    //to avoid exposing DriverManager directly to subclasses now Driver can be accessed through BasePage
    protected WebDriver getDriver() { return DriverManager.getDriver(); }

    //To wait for list of Elements
    protected List<WebElement> waitForAllVisible(By locator) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigManager.getTimeout()))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    //Streams to get the values from List<WebElements>
    protected List<String> getAllTexts(By locator) {
        return waitForAllVisible(locator).stream()
                .map(WebElement::getText)
                .toList();
    }

    protected void waitForTextPresent(By locator, String text) {
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigManager.getTimeout()))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    //JavaScriptExecutor to address the scroll issue in CartPage
    protected void waitForPageToSettle() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigManager.getTimeout()))
                .until(d -> {
                    Object y = ((JavascriptExecutor) d).executeScript("return window.scrollY");
                    return y instanceof Number n && n.doubleValue() == 0;
                });
    }
}
