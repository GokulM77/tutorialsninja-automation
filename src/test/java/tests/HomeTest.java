package tests;

import framework.base.BaseTest;
import framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void verifyPageTitleIsCorrect() {
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getCurrentPageTitle(),"Your Store");
    }

    @Test
    public void verifyLogoIsDisplayed() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isLogoDisplayed());
    }

    @Test
    public void verifyLogoTextIsCorrect() {
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getLogoText(),"Qafox.com");
    }

    @Test
    public void verifyMyAccountLinkIsDisplayed() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isMyAccountDisplayed());
    }
}
