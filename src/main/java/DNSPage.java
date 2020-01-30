import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DNSPage extends BasePageObject{
    WebDriver driver;

    @FindBy(xpath = "//nav[@id='header-search']//input")
    WebElement search;

    @FindBy(xpath = "//nav//a[@data-commerce-target = 'CART']")
    WebElement cartButton;

    DNSPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void findProduct(String searchLine) {
        search.click();
        search.sendKeys(searchLine);
        search.sendKeys(Keys.ENTER);
    }

    public void goToCart() {
        cartButton.click();
    }

    public int getSumFromCart() {
        return Integer.parseInt(driver.findElement(By.xpath("//span[@class='cart-link__price']"))
                .getText().replace(" ", ""));
    }
}
