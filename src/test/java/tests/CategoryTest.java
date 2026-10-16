package tests;

import framework.base.BaseTest;
import framework.pages.CategoryPage;
import framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import static constant.TestGroup.REGRESSION;

public class CategoryTest extends BaseTest {

    @Test(groups = REGRESSION)
    public void validateComponentSection() {
        CategoryPage categoryPage = new HomePage().navigateToComponents();
        verifySortingWorks(categoryPage);
    }

    @Test(groups = REGRESSION)
    public void validatePhoneSection() {
        CategoryPage categoryPage = new HomePage().navigateToPhones();
        verifySortingWorks(categoryPage);
    }

    private void verifySortingWorks(CategoryPage categoryPage) {
        String firstProduct = categoryPage.getFirstProductName();
        categoryPage.sortBy("Price (High > Low)");
        String sortedProduct = categoryPage.getFirstProductName();

        Assert.assertNotEquals(firstProduct, sortedProduct, "The product order did not change after sorting!");
    }
}
