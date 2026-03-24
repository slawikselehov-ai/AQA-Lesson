import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTest {

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

    // 1. Проверка названия блока
    @Test
    void checkBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(text(),'Онлайн пополнение без комиссии')]"));
        assertTrue(blockTitle.isDisplayed());
    }

    // 2. Проверка логотипов платежных систем
    @Test
    void checkPaymentLogos() {
        List<WebElement> logos = driver.findElements(By.xpath("//img[contains(@src,'visa') or contains(@src,'mastercard')]"));
        assertTrue(logos.size() > 0);
    }

    // 3. Проверка ссылки "Подробнее о сервисе"
    @Test
    void checkMoreInfoLink() {
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        link.click();

        // проверка перехода (URL изменился)
        assertTrue(driver.getCurrentUrl().contains("help"));
    }

    // 4. Заполнение формы
    @Test
    void checkForm() {

        // ввод номера
        WebElement phoneInput = driver.findElement(By.xpath("//input[@placeholder='Номер телефона']"));
        phoneInput.sendKeys("297777777");

        // ввод суммы
        WebElement amountInput = driver.findElement(By.xpath("//input[@placeholder='Сумма']"));
        amountInput.sendKeys("10");

        // кнопка продолжить
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Продолжить')]"));
        button.click();

        // проверка что переход произошел
        assertTrue(driver.getCurrentUrl().contains("payment"));
    }
}
