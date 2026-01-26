package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Класс страницы поиска заказа
 */
public class YandexScooterTrackOrderPage {
    private final WebDriver driver;
    // Картинка "Такого заказа нет" на странице поиска заказа
    private final By trackOrderErrorImage =
            By.xpath("//*[@class='Track_NotFound__6oaoY']/img[@alt='Not found']");

    public YandexScooterTrackOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Метод нажимает на кнопку "Go!" при вводе номера заказа в хедере
     */
    public boolean checkTrackOrderErrorImageVisibility() {
        return driver.findElement(trackOrderErrorImage).isDisplayed();
    }
}
