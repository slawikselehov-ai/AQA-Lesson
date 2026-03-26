import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTest2 {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.mts.by/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    // 1. Проверка плейсхолдеров (надписей) в полях
    @Test
    void checkPlaceholders() {

        // Услуги связи
        WebElement phone = driver.findElement(By.xpath("//input[@placeholder='Номер телефона']"));
        assertEquals("Номер телефона", phone.getAttribute("placeholder"));

        WebElement sum = driver.findElement(By.xpath("//input[@placeholder='Сумма']"));
        assertEquals("Сумма", sum.getAttribute("placeholder"));

        // можно аналогично проверить другие вкладки (пример для новичка)
        // Домашний интернет (примерный xpath)
        driver.findElement(By.xpath("//span[contains(text(),'Домашний интернет')]")).click();

        WebElement login = driver.findElement(By.xpath("//input[@placeholder='Логин']"));
        assertTrue(login.isDisplayed());
    }

    // 2. Проверка формы и модального окна
    @Test
    void checkPaymentFlow() {

        // ввод данных
        WebElement phoneInput = driver.findElement(By.xpath("//input[@placeholder='Номер телефона']"));
        phoneInput.sendKeys("297777777");

        WebElement sumInput = driver.findElement(By.xpath("//input[@placeholder='Сумма']"));
        sumInput.sendKeys("10");

        // нажимаем кнопку
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Продолжить')]"));
        button.click();

        // --- проверки в модальном окне ---

        // проверка суммы
        WebElement amount = driver.findElement(By.xpath("//div[contains(text(),'10')]"));
        assertTrue(amount.isDisplayed());

        // проверка телефона
        WebElement phoneResult = driver.findElement(By.xpath("//div[contains(text(),'297777777')]"));
        assertTrue(phoneResult.isDisplayed());

        // поля карты
        WebElement cardNumber = driver.findElement(By.xpath("//input[@placeholder='Номер карты']"));
        assertEquals("Номер карты", cardNumber.getAttribute("placeholder"));

        WebElement expire = driver.findElement(By.xpath("//input[@placeholder='Срок действия']"));
        assertEquals("Срок действия", expire.getAttribute("placeholder"));

        WebElement cvv = driver.findElement(By.xpath("//input[@placeholder='CVV']"));
        assertEquals("CVV", cvv.getAttribute("placeholder"));

        // проверка логотипов платёжных систем
        List<WebElement> logos = driver.findElements(
                By.xpath("//img[contains(@src,'visa') or contains(@src,'mastercard')]")
        );

        assertTrue(logos.size() > 0);
    }
}
