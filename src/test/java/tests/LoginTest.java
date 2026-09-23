package tests;

import framework.base.BaseTest;
import framework.config.ConfigManager;
import framework.pages.HomePage;
import framework.pages.LoginPage;
import framework.pages.MyAccountPage;
import framework.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {TestDataUtils.generateRandomEmail(),TestDataUtils.generateRandomPassword()},
                {TestDataUtils.generateRandomEmail(),""},
                {"",TestDataUtils.generateRandomPassword()},
                {"",""}
        };
    }

    @Test
    public void verifyValidLogin() {
        LoginPage loginPage = new HomePage().navigateToLoginPage();
        MyAccountPage myAccountPage = loginPage.loginWithValidCredentials(ConfigManager.getValidEmail(), ConfigManager.getValidPassword());

        Assert.assertEquals(myAccountPage.myAccountHeader(),"My Account");
    }

    @Test(dataProvider = "invalidLoginData")
    public void verifyInvalidLogin(String email, String password) {
        LoginPage loginPage = new HomePage().navigateToLoginPage();
        loginPage.loginWithInvalidCredentials(email, password);

        Assert.assertEquals(loginPage.getErrorMessage(), "Warning: No match for E-Mail Address and/or Password.");
    }
}
