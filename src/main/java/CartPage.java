import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends DNSPage{

    @FindBy(xpath = "//span[@class='cart-link__badge']")
    WebElement amountOfGoods;

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public int amountOfGoods() {
        return Integer.parseInt(amountOfGoods.getText());
    }

    public boolean goodHasTwoYearsGuarantee(int goodIndex) {
        List<WebElement> goodsInCart = driver
                .findElements(By.xpath("//div[@class='cart-items__product-thumbnail cart-items__product-thumbnail_product']"));
        return goodsInCart.get(goodIndex)
                .findElement(By.xpath(".//span[contains(text(),'Продленная гарантия на 24')]")).isDisplayed();
    }

    public int goodInCartPrice(int goodIndex) {
        List<WebElement> goodsInCart = driver
                .findElements(By.xpath("//div[@class='cart-items__product-thumbnail cart-items__product-thumbnail_product']"));
        return Integer.parseInt(goodsInCart.get(goodIndex)
                .findElement(By.xpath(".//span[@class='price__current']")).getText().replace(" ", ""));
    }

    public void deleteGood(int goodIndex) {
        List<WebElement> goodsInCart = driver
                .findElements(By.xpath("//div[@class='cart-items__product-thumbnail cart-items__product-thumbnail_product']"));
        goodsInCart.get(goodIndex)
                .findElement(By.xpath(".//button[@class='menu-control-button' and .='Удалить']")).click();
    }

    public void addSameGood(int goodIndex, int numberOfClicks) {
        for(int i = 0; i < numberOfClicks; i++) {
            List<WebElement> goodsInCart = driver
                    .findElements(By.xpath("//div[@class='cart-items__product-thumbnail cart-items__product-thumbnail_product']"));
            goodsInCart.get(goodIndex).findElement(By.xpath(".//i[@class='count-buttons__icon-plus']")).click();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
