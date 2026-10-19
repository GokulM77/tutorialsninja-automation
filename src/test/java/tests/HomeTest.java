package tests;

import framework.base.BaseTest;
import framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import static constant.TestGroup.REGRESSION;
import static constant.TestGroup.SMOKE;

public class HomeTest extends BaseTest {

    @Test(groups = REGRESSION)
    public void verifyPageTitleIsCorrect() {
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getCurrentPageTitle(),"Your Store");
    }

    @Test(groups = {SMOKE,REGRESSION})
    public void verifyLogoIsDisplayed() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isLogoDisplayed());
    }

    @Test(groups = REGRESSION)
    public void verifyLogoTextIsCorrect() {
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getLogoText(),"Qafox.com");
    }

    @Test(groups = REGRESSION)
    public void verifyMyAccountLinkIsDisplayed() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isMyAccountDisplayed());
    }
}
