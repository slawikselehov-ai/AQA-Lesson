package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.CartPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class WildberriesTest {

    WebDriver driver;
    MainPage mainPage;
    CartPage cartPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.wildberries.ru/");

        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testAddToCartAndCheck() {

        // добавляем 2 товара
        mainPage.addFirstProductsToCart(2);

        // переходим в корзину
        mainPage.openCart();

        // проверяем количество товаров
        int count = cartPage.getItemsCount();
        assertTrue(count > 0);

        // проверяем названия
        assertFalse(cartPage.getItemNames().isEmpty());

        // проверяем цены
        assertFalse(cartPage.getItemPrices().isEmpty());

        // проверяем общую сумму
        String total = cartPage.getTotalPrice();
        assertNotNull(total);
        assertFalse(total.isEmpty());
    }
}
