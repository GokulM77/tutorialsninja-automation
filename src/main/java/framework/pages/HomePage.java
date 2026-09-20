package framework.pages;

import framework.base.BasePage;
import framework.components.HeaderComponent;

public class HomePage extends BasePage {

    private HeaderComponent header = new HeaderComponent();

    public boolean isLogoDisplayed() { return header.isLogoDisplayed(); }

    public boolean isMyAccountDisplayed() { return header.isMyAccountDisplayed(); }

    public String getLogoText() {
        return header.getLogoText();
    }

    public String getCurrentPageTitle() {
        return getPageTitle();
    }

    public LoginPage navigateToLoginPage() { return header.navigateToLoginPage();}
}
