package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;

public class CategoryPage extends BasePage {

    private By firstProductNameLocator = By.xpath("(//div[contains(@class,'product-thumb')]//h4/a)[1]");
    private By sortLocator = By.id("input-sort");
    private By firstProductAddToCartLocator = By.xpath("(//div[contains(@class,'product-thumb')]//button[contains(@onclick,'cart.add')])[1]");
    private By secondProductAddToCartLocator = By.xpath("(//div[contains(@class,'product-thumb')]//button[contains(@onclick,'cart.add')])[2]");
    private By cartTotalLocator = By.id("cart-total");

    public void sortBy(String optionText) { selectByVisibleText(sortLocator,optionText); }

    public String getFirstProductName() {
        return getText(firstProductNameLocator);
    }

    private void addToCart(By addButton, int expectedCartCount) {
        click(addButton);
        waitForTextPresent(cartTotalLocator, expectedCartCount + " item(s)");
        waitForPageToSettle();
    }

    public void addFirstProductToCart(int expectedCartCount) {
        addToCart(firstProductAddToCartLocator, expectedCartCount);
    }

    public void addSecondProductToCart(int expectedCartCount) {
        addToCart(secondProductAddToCartLocator, expectedCartCount);
    }
}
