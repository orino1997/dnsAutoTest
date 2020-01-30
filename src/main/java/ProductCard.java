import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductCard extends DNSPage{
    @FindBy(xpath = "//button[@class='btn btn-cart btn-lg']")
    WebElement buyButton;

    @FindBy(xpath = "//div[@class='hidden-xs hidden-sm price-block-wrap price-block-wrap_view_desktop']" +
            "//span[@class='current-price-value']")
    WebElement price;

    @FindBy(xpath = "//select[@class='form-control select']")
    WebElement guaranteeButton;

    @FindBy(xpath = "//div[@class='price-item-description']//p")
    WebElement description;

    @FindBy(xpath = "//div[@class='rating-block-wrap']//span[@class='available']")
    WebElement isAvailable;

    ProductCard(WebDriver driver) {
        super(driver);
    }

    public void addGuarantee(int a) {
        guaranteeButton.click();
        Select guarantee = new Select(guaranteeButton);
        guarantee.selectByIndex(a);
    }
    public int getPriceOfProduct() {
        return Integer.parseInt(price.getText().replace(" ", ""));
    }
}
