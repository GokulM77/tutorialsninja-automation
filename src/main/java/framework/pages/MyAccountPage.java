package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;

public class MyAccountPage extends BasePage {

    private By myAccountHeaderLocator = By.xpath("//div[@id='content']/h2[1]");

    public String myAccountHeader() {
        return getText(myAccountHeaderLocator);
    }
}
