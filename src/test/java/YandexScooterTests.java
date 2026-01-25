import constants.Browser;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.YandexScooterHeaderPage;
import pom.YandexScooterHomePage;
import pom.YandexScooterOrderPage;
import testdata.Client;

import java.time.Duration;

import static constants.FormTitle.*;
import static constants.Url.*;
import static org.junit.jupiter.api.Assertions.*;

public class YandexScooterTests {
    private WebDriver driver;

//    @BeforeEach
//    void setUp() {
//    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#faqTestData")
    void faqSpoilerDescriptionTest(Browser browser, String faqTitle, String expectedResult) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);
        YandexScooterHomePage objHomePage = new YandexScooterHomePage(driver, faqTitle);
        objHomePage.clickFaqSpoiler();

        assertEquals(expectedResult, objHomePage.getFaqSpoilerDescription());
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#orderTestData")
    void completeOrderViaHeaderOrderButton(Browser browser, Client client) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);
        YandexScooterHeaderPage objHeaderPage = new YandexScooterHeaderPage(driver);
        objHeaderPage.clickHeaderOrderButton();

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.urlToBe(URL_ORDER_PAGE));

        YandexScooterOrderPage objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.createOrderViaHeaderOrderButton(
                client.getFirstName(),
                client.getFamilyName(),
                client.getAddress(),
                client.getTelNumber()
        );

        assertTrue(objOrderPage.getCompleteOrderTitleText().contains(TITLE_COMPLETE_ORDER_FORM),
                "Не найдено окно об успешном формировании заказа");
    }

    @ParameterizedTest
    @MethodSource("testdata.ParameterizedTestData#orderTestData")
    void completeOrderViaMiddleOrderButton(Browser browser, Client client) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);
        YandexScooterHomePage objHomePage = new YandexScooterHomePage(driver);
        objHomePage.clickOrderButton();

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.urlToBe(URL_ORDER_PAGE));

        YandexScooterOrderPage objOrderPage = new YandexScooterOrderPage(driver);
        objOrderPage.createOrderViaHeaderOrderButton(
                client.getFirstName(),
                client.getFamilyName(),
                client.getAddress(),
                client.getTelNumber()
        );

        assertTrue(objOrderPage.getCompleteOrderTitleText().contains(TITLE_COMPLETE_ORDER_FORM),
                "Не найдено окно об успешном формировании заказа");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
