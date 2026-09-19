package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By logoLocator = By.id("logo");
    private By searchBoxLocator = By.name("search");
    private By myAccountLocator = By.cssSelector("a[title='My Account']");
    private By accountLoginLocator = By.xpath("//a[text()='Login']");

    public boolean isLogoDisplayed() {
        return isDisplayed(logoLocator);
    }

    public boolean isMyAccountDisplayed() {
        return isDisplayed(myAccountLocator);
    }

    public boolean isSearchBoxDisplayed() {
        return isDisplayed(searchBoxLocator);
    }

    public String getLogoText() {
        return getText(logoLocator);
    }

    public String getCurrentPageTitle() {
        return getPageTitle();
    }

    public LoginPage navigateToLoginPage() {
        click(myAccountLocator);
        click(accountLoginLocator);
        return new LoginPage();
    }
}
