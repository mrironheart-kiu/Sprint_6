import constants.Browser;
import constants.FaqTitle;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import pom.HomePageYandexScooter;

import java.util.stream.Stream;

import static constants.Browser.*;
import static constants.FaqDescription.*;
import static constants.FaqTitle.*;
import static constants.Url.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YandexScooterTests {
    private WebDriver driver;

//    @BeforeEach
//    void setUp() {
//    }

    @ParameterizedTest
    @MethodSource("credentialsProviderExample")
    void checkFaqSpoilerDescriptionTest(Browser browser, String faqTitle, String expectedResult) {
        driver = new WebDriverFactory().getWebDriver(browser);
        driver.get(URL_MAIN_PAGE);

        HomePageYandexScooter objHomePage = new HomePageYandexScooter(driver, faqTitle);

        objHomePage.clickFaqSpoiler();

        assertEquals(expectedResult, objHomePage.getFaqSpoilerDescription());
    }

    private static Stream<Arguments> credentialsProviderExample() {
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

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
