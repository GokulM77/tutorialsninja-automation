package tests;

import framework.base.BaseTest;
import framework.pages.HomePage;
import framework.pages.LoginPage;
import framework.pages.MyAccountPage;
import framework.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    // TODO: Phase 5 — replace with data-driven source (JSON/Excel/DataProvider) once test-data layer exists
    private static final String VALID_EMAIL = "peterp12@gmail.com";
    private static final String VALID_PASSWORD = "Peter@12#";
    private String invalidEmail = TestDataUtils.generateRandomEmail();
    private static final String INVALID_PASSWORD = "hehe";

    @Test
    public void verifyValidLogin() {
        LoginPage loginPage = new HomePage().navigateToLoginPage();
        MyAccountPage myAccountPage = loginPage.loginWithValidCredentials(VALID_EMAIL, VALID_PASSWORD);

        Assert.assertEquals(myAccountPage.myAccountHeader(),"My Account");
    }

    @Test
    public void verifyInvalidLogin() {
        LoginPage loginPage = new HomePage().navigateToLoginPage();
        loginPage.loginWithInvalidCredentials(invalidEmail, INVALID_PASSWORD);

        Assert.assertEquals(loginPage.getErrorMessage(), "Warning: No match for E-Mail Address and/or Password.");
    }
}
