package framework.pages;

import framework.base.BasePage;
import framework.components.HeaderComponent;
import framework.components.NavigationComponent;

public class HomePage extends BasePage {

    private HeaderComponent header = new HeaderComponent();
    private NavigationComponent navigation = new NavigationComponent();

    public boolean isLogoDisplayed() { return header.isLogoDisplayed(); }

    public boolean isMyAccountDisplayed() { return header.isMyAccountDisplayed(); }

    public String getLogoText() {
        return header.getLogoText();
    }

    public String getCurrentPageTitle() {
        return getPageTitle();
    }

    public LoginPage navigateToLoginPage() { return header.navigateToLoginPage();}

    public CategoryPage navigateToComponents() { return navigation.navigateToComponents();}

    public CategoryPage navigateToPhones() { return navigation.navigateToPhones();}

    public CartPage navigateToShoppingCart() { return header.navigateToShoppingCart();}
}