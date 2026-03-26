package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // список товаров
    private By products = By.xpath("//article");

    // кнопка "в корзину"
    private By addToCartButtons = By.xpath("//button[contains(text(),'В корзину')]");

    public void addFirstProductsToCart(int count) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);

        for (int i = 0; i < count && i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }

    public void openCart() {
        driver.findElement(By.xpath("//a[contains(@href,'basket')]")).click();
    }
}
