import constants.Browser;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.YandexScooterHeaderPage;
import pom.YandexScooterHomePage;
import pom.YandexScooterOrderPage;
import pom.YandexScooterTrackOrderPage;
import testdata.Client;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static constants.ErrorMessage.*;
import static constants.FormTitle.*;
import static constants.Url.*;
import static org.junit.jupiter.api.Assertions.*;

public class YandexScooterTests {
    private WebDriver driver;
    // Домашняя страница сайт "Яндекс Самокат"
    private YandexScooterHomePage objHomePage;
    // Хедер сайт "Яндекс Самокат"
    private YandexScooterHeaderPage objHeaderPage;
    // Страница формирования заказа сайт "Яндекс Самокат"
    private YandexScooterOrderPage objOrderPage;
    // Страница поиска заказа сайт "Яндекс Самокат"
    private YandexScooterTrackOrderPage objTrackOrderPage;

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#faqTestData")
    void faqSpoilerDescriptionTest(Browser browser, String faqTitle, String expectedResult) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);
        objHomePage = new YandexScooterHomePage(driver, faqTitle);
        objHomePage.clickFaqSpoiler();

        assertEquals(expectedResult, objHomePage.getFaqSpoilerDescription());
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#orderTestData")
    void completeOrderViaHeaderOrderButton(Browser browser, Client client) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);
        objHeaderPage = new YandexScooterHeaderPage(driver);
        objHeaderPage.clickHeaderOrderButton();

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.urlToBe(URL_ORDER_PAGE));

        objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.createOrder(
                client.getFirstName(),
                client.getFamilyName(),
                client.getAddress(),
                client.getPhoneNumber()
        );

        assertTrue(objOrderPage.getCompleteOrderTitleText().contains(TITLE_COMPLETE_ORDER_FORM),
                "Не найдено окно об успешном формировании заказа");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#orderTestData")
    void completeOrderViaMiddleOrderButton(Browser browser, Client client) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);
        objHomePage = new YandexScooterHomePage(driver);
        objHomePage.clickOrderButton();

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.urlToBe(URL_ORDER_PAGE));

        objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.createOrder(
                client.getFirstName(),
                client.getFamilyName(),
                client.getAddress(),
                client.getPhoneNumber()
        );

        assertTrue(objOrderPage.getCompleteOrderTitleText().contains(TITLE_COMPLETE_ORDER_FORM),
                "Не найдено форма об успешном формировании заказа");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#browserTestData")
    void scooterLogoRedirectsToMainPage(Browser browser) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_ORDER_PAGE);

        objHeaderPage = new YandexScooterHeaderPage(driver);
        objHeaderPage.clickHeaderScooterLogo();

        assertEquals(URL_MAIN_PAGE + "/", driver.getCurrentUrl(),
                "Не выполнен переход на главную страницу Яндекс Самокат");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#browserTestData")
    void yandexLogoRedirectsToYandexMainPage(Browser browser) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);

        objHeaderPage = new YandexScooterHeaderPage(driver);
        objHeaderPage.clickHeaderYandexLogo();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowIds = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(windowIds);
        driver.switchTo().window(tabs.get(1));
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.urlContains(URL_YANDEX_MAIN_PAGE));

        assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(URL_YANDEX_MAIN_PAGE),
                "Не выполнен переход на главную страницу Яндекс");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#browserTestData")
    @DisplayName("Отображается корректный текст ошибки для поля \"Имя\" первой формы заказа")
    void orderPageFirstNameFieldDisplaysErrorMessages(Browser browser) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_ORDER_PAGE);

        objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.clickNextButton();

        assertEquals(MESSAGE_ERROR_ORDER_FIRST_NAME,
                objOrderPage.getErrorMessage(objOrderPage.getFirstNameField()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#browserTestData")
    @DisplayName("Отображается корректный текст ошибки для поля \"Фамилия\" первой формы заказа")
    void orderPageFamilyNameFieldDisplaysErrorMessages(Browser browser) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_ORDER_PAGE);

        objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.clickNextButton();

        assertEquals(MESSAGE_ERROR_ORDER_FAMILY_NAME,
                objOrderPage.getErrorMessage(objOrderPage.getFamilyNameField()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#browserTestData")
    @DisplayName("Отображается корректный текст ошибки для поля \"Адрес\" первой формы заказа")
    void orderPageAddressFieldDisplaysErrorMessages(Browser browser) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_ORDER_PAGE);

        objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.setAddress("1");
        objOrderPage.clickNextButton();

        assertEquals(MESSAGE_ERROR_ORDER_ADDRESS,
                objOrderPage.getErrorMessage(objOrderPage.getAddressField()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#browserTestData")
    @DisplayName("Отображается корректный текст ошибки для поля \"Станция метро\" первой формы заказа")
    void orderPageMetroStationFieldDisplaysErrorMessages(Browser browser) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_ORDER_PAGE);

        objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.clickNextButton();

        assertEquals(MESSAGE_ERROR_ORDER_METRO_STATION,
                objOrderPage.getErrorMessage(objOrderPage.getMetroStationField()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#browserTestData")
    @DisplayName("Отображается корректный текст ошибки для поля \"Телефон\" первой формы заказа")
    void orderPagePhoneNumberFieldDisplaysErrorMessages(Browser browser) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_ORDER_PAGE);

        objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.clickNextButton();

        assertEquals(MESSAGE_ERROR_ORDER_PHONE,
                objOrderPage.getErrorMessage(objOrderPage.getPhoneNumberField()),
                "Текст ошибки не соответствует ожидаемому");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#browserTestData")
    @DisplayName("Отображается картинка о том что заказ не существует")
    void trackOrderPageDisplaysErrorImage(Browser browser) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);

        objHeaderPage = new YandexScooterHeaderPage(driver);
        objHeaderPage.clickOrderStatusButton();
        objHeaderPage.clickOrderStatusConfirmButton();

        objTrackOrderPage = new YandexScooterTrackOrderPage(driver);

        assertTrue(objTrackOrderPage.checkTrackOrderErrorImageVisibility(),
                "Картинка \"Такого заказа нет\" не отображается");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
