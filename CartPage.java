package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // товары в корзине
    private By cartItems = By.xpath("//div[contains(@class,'basket-item')]");

    // названия
    private By itemNames = By.xpath("//a[contains(@class,'basket-item__name')]");

    // цены
    private By itemPrices = By.xpath("//span[contains(@class,'price')]");

    // общая сумма
    private By totalPrice = By.xpath("//span[contains(@class,'total')]");

    public int getItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public List<WebElement> getItemNames() {
        return driver.findElements(itemNames);
    }

    public List<WebElement> getItemPrices() {
        return driver.findElements(itemPrices);
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }
}
