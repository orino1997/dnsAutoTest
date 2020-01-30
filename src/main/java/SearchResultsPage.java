import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends DNSPage {
    public List<WebElement> goods;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement neededGoodChoice() {
        goods = driver.findElements(By.xpath("//div[@class='product-info__title-link']//a"));
        return goods.get(0);
    }
}
