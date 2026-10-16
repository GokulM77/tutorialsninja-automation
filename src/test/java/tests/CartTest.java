package tests;

import framework.base.BaseTest;
import framework.listeners.RetryAnalyzer;
import framework.pages.CartPage;
import framework.pages.CategoryPage;
import framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import static constant.TestGroup.SMOKE;
import static constant.TestGroup.REGRESSION;

import java.math.BigDecimal;

public class CartTest extends BaseTest {

    @Test(groups = {SMOKE,REGRESSION},retryAnalyzer = RetryAnalyzer.class)
    public void validateShoppingCart() {
        CategoryPage categoryPage = new HomePage().navigateToPhones();
        categoryPage.addFirstProductToCart(1);
        categoryPage.addSecondProductToCart(2);
        CartPage cart = new HomePage().navigateToShoppingCart();

        Assert.assertEquals(cart.getProductNames().size(),2,"Product size doesn't match");
        BigDecimal total = cart.getProductTotals().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Assert.assertEquals(total.compareTo(cart.getDisplayedTotal()), 0, "Total doesn't match");

        //Validating if price is recalculated based on new shipping details
        cart.clickEstimateShippingTaxButton().selectCountry("India").selectRegion("Karnataka").enterPostCode("1234").getQuotes();
        cart.selectAndApplyShipping();

        BigDecimal updatedTotal = cart.getProductTotals().stream()
                .reduce(cart.getFlatShippingRate(), BigDecimal::add);
        Assert.assertEquals(updatedTotal.compareTo(cart.getDisplayedTotal()), 0, "Updated Total doesn't match");
    }
}
