package stepdefinitions;

import framework.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class HomeSteps {

    @Given("the user is on the home page")
    public void userIsOnHomePage() {
        HomePage homePage = new HomePage();
        Assert.assertEquals(homePage.getCurrentPageTitle(),"Your Store");
    }

    @Then("the store logo should be displayed")
    public void logoIsDisplayed() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isLogoDisplayed());
    }
}
