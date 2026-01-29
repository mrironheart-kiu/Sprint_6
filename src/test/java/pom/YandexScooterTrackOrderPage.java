package pom;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

/**
 * Класс страницы поиска заказа
 */
@RequiredArgsConstructor
public class YandexScooterTrackOrderPage {
    private final WebDriver driver;
    // Картинка "Такого заказа нет" на странице поиска заказа
    private final By trackOrderErrorImage =
            By.xpath("//*[@class='Track_NotFound__6oaoY']/img[@alt='Not found']");

    /**
     * Метод нажимает на кнопку "Go!" при вводе номера заказа в хедере
     */
    public boolean checkTrackOrderErrorImageVisibility() {
        WaitUtils.waitToBeVisibable(driver, trackOrderErrorImage);
        return driver.findElement(trackOrderErrorImage).isDisplayed();
    }
}
