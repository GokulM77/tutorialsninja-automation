package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import java.math.BigDecimal;
import java.util.List;

public class CartPage extends BasePage {

    private By totalLocator = By.xpath("//td[strong[text()='Total:']]//following-sibling::td");
    private By productNameLocator = By.xpath("//div[@class='table-responsive']//table/tbody/tr/td[2]/a");
    private By productTotalLocator = By.xpath("//div[@class='table-responsive']//table/tbody/tr/td[6]");
    private By estimateShippingTaxLocator = By.xpath("//a[text()='Estimate Shipping & Taxes ']");
    private By selectCountryLocator = By.id("input-country");
    private By selectRegionLocator = By.id("input-zone");
    private By postCodeLocator = By.id("input-postcode");
    private By getQuoteButtonLocator = By.id("button-quote");
    private By flatRateRadioButtonLocator = By.name("shipping_method");
    private By applyShippingButtonLocator = By.id("button-shipping");
    private By flatShippingRateLocator = By.xpath("//td[strong[text()='Flat Shipping Rate:']]//following-sibling::td");

    public List<String> getProductNames() { return getAllTexts(productNameLocator);}

    public List<BigDecimal> getProductTotals() {
        //streams to remove $ and , from total string and save as BigDecimal
        return getAllTexts(productTotalLocator).stream()
                .map(text -> text.replaceAll("[$,]", ""))
                .map(BigDecimal::new)
                .toList();
    }

    public BigDecimal getDisplayedTotal() {
        String updatedTotal = getText(totalLocator).replaceAll("[$,]","");
        //typecasting to BigDecimal for assertion in CartTest
        return new BigDecimal(updatedTotal);
    }

    public BigDecimal getFlatShippingRate() {
        String updatedTotal = getText(flatShippingRateLocator).replaceAll("[$,]","");
        //typecasting to BigDecimal for assertion in CartTest
        return new BigDecimal(updatedTotal);
    }

    public CartPage clickEstimateShippingTaxButton() { click(estimateShippingTaxLocator); return this;}

    public CartPage selectCountry(String country) {
        waitForTextPresent(selectCountryLocator, country);
        selectByVisibleText(selectCountryLocator,country);
        return this;
    }

    public CartPage selectRegion(String region) {
        waitForTextPresent(selectRegionLocator, region);
        selectByVisibleText(selectRegionLocator,region);
        return this;
    }

    public CartPage enterPostCode(String postcode) { type(postCodeLocator,postcode); return this;}

    public void getQuotes() { click(getQuoteButtonLocator);}

    public void selectAndApplyShipping() {
        click(flatRateRadioButtonLocator);
        click(applyShippingButtonLocator);
        waitForVisible(flatShippingRateLocator);
    }
}
