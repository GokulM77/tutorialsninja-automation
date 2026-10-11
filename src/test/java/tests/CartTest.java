package tests;

import framework.base.BaseTest;
import framework.pages.CartPage;
import framework.pages.CategoryPage;
import framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class CartTest extends BaseTest {

    @Test
    public void validateShoppingCart() {
        CategoryPage categoryPage = new HomePage().navigateToPhones();
        categoryPage.addFirstProductToCart(1);
        categoryPage.addSecondProductToCart(2);
        CartPage cart = new HomePage().navigateToShoppingCart();

        Assert.assertEquals(cart.getProductNames().size(),2,"Product size doesn't match");
        BigDecimal total = cart.getProductTotals().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.assertEquals(total.compareTo(cart.getDisplayedTotal()), 0, "Total doesn't match");
    }
}
