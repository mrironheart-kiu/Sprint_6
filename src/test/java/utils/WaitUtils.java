package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс с методми ожидания веб-элементов
 */
public class WaitUtils {
    // Задержка в секундах по умолчанию
    private static final int DEFAULT_TIMEOUT_SECONDS = 5;

    /**
     * Метод ожидает когда веб-элемент станет кликабелен в течении времени по умолчанию
     *
     * @param driver     веб-драйвер по умолчанию
     * @param webElement веб-элемент страницы
     */
    public static void waitToBeClickable(WebDriver driver, WebElement webElement) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Метод ожидает когда веб-элемент станет виден в течении времени по умолчанию
     *
     * @param driver  веб-драйвер по умолчанию
     * @param locator локатор веб-элемента страницы
     */
    public static void waitToBeVisibable(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Метод ожидает указанный URL в течении времени по умолчанию
     *
     * @param driver веб-драйвер по умолчанию
     * @param url    ожидаемый URL
     */
    public static void waitUrlToBe(WebDriver driver, String url) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.urlToBe(url));
    }

    /**
     * Метод ожидает что указанная строка содержится в URL в течении времени по умолчанию
     *
     * @param driver веб-драйвер по умолчанию
     * @param s      ожидаемая строка
     */
    public static void waitUrlContains(WebDriver driver, String s) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.urlContains(s));
    }

    /**
     * Метод ожидает указанное кол-во вкладок браузера в течении времени по умолчанию
     *
     * @param driver      веб-драйвер по умолчанию
     * @param umberOfTabs ожидаемое кол-во вкладок браузера
     */
    public static void waitNumberOfTabs(WebDriver driver, int umberOfTabs) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.numberOfWindowsToBe(umberOfTabs));
    }
}
