package stepdefinitions;

import framework.config.ConfigManager;
import framework.pages.HomePage;
import framework.pages.LoginPage;
import framework.pages.MyAccountPage;
import framework.utils.TestDataUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.function.Supplier;

public class LoginSteps {

    private String resolve(String credential, Supplier<String> random) {
        return switch(credential.toLowerCase()) {
            case "random" -> random.get();
            case "empty" -> "";
            default -> throw new IllegalArgumentException("Unknown test data used : "+ credential);
        };
    }

    @Given("User is on the login page")
    public void userIsOnLoginPage() {
        new HomePage().navigateToLoginPage();
    }

    @When("User enters the registered email and password")
    public void entersRegisteredCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(ConfigManager.getValidEmail());
        loginPage.enterPassword(ConfigManager.getValidPassword());
    }

    @When("User enters email {string} and password {string}")
    public void loginWithInvalidCredentials(String email, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.enterEmail(resolve(email, TestDataUtils::generateRandomEmail));
        loginPage.enterPassword(resolve(password, TestDataUtils::generateRandomPassword));
    }

    @And("User clicks on Login button")
    public void clickLogin() { new LoginPage().clickLoginButton();}

    @Then("User should land on My Account page")
    public void landsOnAccount() {
        Assert.assertEquals(new MyAccountPage().myAccountHeader(), "My Account");
    }

    @Then("User should see the warning {string}")
    public void errorMessage(String error) {
        Assert.assertEquals(new LoginPage().getErrorMessage(), error);
    }
}
