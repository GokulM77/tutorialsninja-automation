package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By emailLocator = By.id("input-email");
    private By passwordLocator = By.id("input-password");
    private By loginButtonLocator = By.cssSelector("input[value='Login']");
    private By errorMessageLocator = By.cssSelector("div.alert-danger");

    private void performLogin(String email, String password) {
        type(emailLocator, email);
        type(passwordLocator, password);
        click(loginButtonLocator);
    }

    public LoginPage loginWithInvalidCredentials(String email, String password) {
        performLogin(email,password);
        return new LoginPage();
    }

    public MyAccountPage loginWithValidCredentials(String email, String password) {
        performLogin(email,password);
        return new MyAccountPage();
    }

    public String getErrorMessage() {
        return getText(errorMessageLocator);
    }
}
