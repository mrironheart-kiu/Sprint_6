package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс хедера страниц Яндекс Самокат
 */
public class YandexScooterHeaderPage {
    private final WebDriver driver;
    // Кнопка "Заказать" в хедере
    private final By faqSpoilerTitle = By.className("Button_Button__ra12g");
    // Надпись "Самокат" в логотипе в хедере
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    // Надпись "Яндекс" в логотипе в хедере
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    public YandexScooterHeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод нажимает на нопку "Заказать" в хедере
     */
    public void clickHeaderOrderButton() {
        WebElement element = driver.findElement(faqSpoilerTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Метод нажимает на надпись "Самокат" в логотипе в хедере
     */
    public void clickHeaderScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    /**
     * Метод нажимает на надпись "Яндекс" в логотипе в хедере
     */
    public void clickHeaderYandexLogo() {
        driver.findElement(yandexLogo).click();
    }
}
