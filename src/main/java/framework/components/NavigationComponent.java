package framework.components;

import framework.base.BasePage;
import framework.pages.CategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NavigationComponent extends BasePage {

    private By componentsMenuLocator = By.xpath("//li[@class='dropdown']/a[text()='Components']");
    private By monitorSubCategoryLocator = By.xpath("//a[contains(@href,'path=25_28')]");
    private By phonesLocator = By.xpath("//a[text()='Phones & PDAs']");

    public CategoryPage navigateToComponents() {
        WebElement menu = waitForVisible(componentsMenuLocator);
        new Actions(getDriver()).moveToElement(menu).perform();
        click(monitorSubCategoryLocator);

        return new CategoryPage();
    }

    public CategoryPage navigateToPhones() {
        click(phonesLocator);

        return new CategoryPage();
    }
}
