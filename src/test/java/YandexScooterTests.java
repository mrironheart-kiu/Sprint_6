import constants.Browser;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.YandexScooterHeaderPage;
import pom.YandexScooterHomePage;
import pom.YandexScooterOrderPage;
import testdata.Client;

import java.time.Duration;
import java.util.stream.Stream;

import static constants.Browser.*;
import static constants.FaqDescription.*;
import static constants.FaqTitle.*;
import static constants.FormTitle.*;
import static constants.Url.*;
import static org.junit.jupiter.api.Assertions.*;

public class YandexScooterTests {
    private WebDriver driver;

//    @BeforeEach
//    void setUp() {
//    }

    @ParameterizedTest
    @MethodSource("faqTestData")
    void faqSpoilerDescriptionTest(Browser browser, String faqTitle, String expectedResult) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);
        YandexScooterHomePage objHomePage = new YandexScooterHomePage(driver, faqTitle);
        objHomePage.clickFaqSpoiler();

        assertEquals(expectedResult, objHomePage.getFaqSpoilerDescription());
    }

    private static Stream<Arguments> faqTestData() {
        return Stream.of(
                // Проверяем в Chrome
                Arguments.of(CHROME, TITLE_PRICE, DESCRIPTION_PRICE),
                Arguments.of(CHROME, TITLE_MULTIPLE_SCOOTERS, DESCRIPTION_MULTIPLE_SCOOTERS),
                Arguments.of(CHROME, TITLE_RENT_TIME, DESCRIPTION_RENT_TIME),
                Arguments.of(CHROME, TITLE_ORDER_TODAY, DESCRIPTION_ORDER_TODAY),
                Arguments.of(CHROME, TITLE_PROLONGATION, DESCRIPTION_PROLONGATION),
                Arguments.of(CHROME, TITLE_CHARGER, DESCRIPTION_CHARGER),
                Arguments.of(CHROME, TITLE_CANCEL_ORDER, DESCRIPTION_CANCEL_ORDER),
                Arguments.of(CHROME, TITLE_SERVICE_AREA, DESCRIPTION_SERVICE_AREA),
                // Проверяем в FireFox
                Arguments.of(FIREFOX, TITLE_PRICE, DESCRIPTION_PRICE),
                Arguments.of(FIREFOX, TITLE_MULTIPLE_SCOOTERS, DESCRIPTION_MULTIPLE_SCOOTERS),
                Arguments.of(FIREFOX, TITLE_RENT_TIME, DESCRIPTION_RENT_TIME),
                Arguments.of(FIREFOX, TITLE_ORDER_TODAY, DESCRIPTION_ORDER_TODAY),
                Arguments.of(FIREFOX, TITLE_PROLONGATION, DESCRIPTION_PROLONGATION),
                Arguments.of(FIREFOX, TITLE_CHARGER, DESCRIPTION_CHARGER),
                Arguments.of(FIREFOX, TITLE_CANCEL_ORDER, DESCRIPTION_CANCEL_ORDER),
                Arguments.of(FIREFOX, TITLE_SERVICE_AREA, DESCRIPTION_SERVICE_AREA)
        );
    }

    @ParameterizedTest
    @MethodSource("orderTestData")
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

    private static Stream<Arguments> orderTestData() {
        return Stream.of(
                // Проверяем в Chrome
                Arguments.of(CHROME, new Client("Василий", "Петров", "ул Московская","+79098087766")),
                Arguments.of(CHROME, new Client("Прохор", "Троцкий", "ул Пупинская","+79996067788")),
                // Проверяем в FireFox
                Arguments.of(FIREFOX, new Client("Василий", "Петров", "ул Московская", "+79098087766")),
                Arguments.of(FIREFOX, new Client("Прохор", "Троцкий", "ул Пупинская","+79996067788"))
        );
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
