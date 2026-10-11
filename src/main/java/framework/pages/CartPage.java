package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import java.math.BigDecimal;
import java.util.List;

public class CartPage extends BasePage {

    private By totalLocator = By.xpath("//td[strong[text()='Total:']]//following-sibling::td");
    private By productNameLocator = By.xpath("//div[@class='table-responsive']//table/tbody/tr/td[2]/a");
    private By productTotalLocator = By.xpath("//div[@class='table-responsive']//table/tbody/tr/td[6]");

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
}
