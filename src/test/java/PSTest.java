import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PSTest extends BaseTest{
    int psInitialPrice;
    int psWithGuaranteePrice;
    int detroitPrice;
    int psAndDetroitPrice;
    @Test
    public void conductPSTest() {
        driver.navigate().to(URL);
        WebDriverWait wait = new WebDriverWait(driver,30);

        DNSPage dns = new DNSPage(driver);
        dns.findProduct("playstation");

        SearchResultsPage searchResult = new SearchResultsPage(driver);
        searchResult.neededGoodChoice();
        searchResult.neededGoodChoice().click();
        ProductCard ps = new ProductCard(driver);

        psInitialPrice = ps.getPriceOfProduct();
        ps.addGuarantee(2);
        psWithGuaranteePrice = ps.getPriceOfProduct();
        ps.buyButton.click();

        ps.findProduct("detroit");
        ProductCard detroit = new ProductCard(driver);
        detroitPrice = detroit.getPriceOfProduct();
        detroit.buyButton.click();

        psAndDetroitPrice = psWithGuaranteePrice + detroitPrice;
        wait.until(ExpectedConditions.visibilityOf(detroit.cartButton));
        Assert.assertEquals(psAndDetroitPrice, detroit.getSumFromCart());

        detroit.goToCart();
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.goodHasTwoYearsGuarantee(0));

        Assert.assertEquals(psInitialPrice, cart.goodInCartPrice(0));
        Assert.assertEquals(detroitPrice, cart.goodInCartPrice(1));
        Assert.assertEquals(psAndDetroitPrice, cart.getSumFromCart());

        cart.deleteGood(1);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, cart.amountOfGoods());
        Assert.assertEquals(psWithGuaranteePrice, cart.getSumFromCart());

        cart.addSameGood(0, 2);
        wait.until(ExpectedConditions.visibilityOf(detroit.cartButton));
        Assert.assertEquals(psWithGuaranteePrice * 3, cart.getSumFromCart());

        cart.findProduct("detroit");
        detroit.buyButton.click();
        detroit.goToCart();
        wait.until(ExpectedConditions.visibilityOf(detroit.cartButton));
        Assert.assertTrue(cart.amountOfGoods() == 4);
        Assert.assertEquals(psWithGuaranteePrice * 3 + detroitPrice, cart.getSumFromCart());
    }
}
