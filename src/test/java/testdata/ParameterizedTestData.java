package testdata;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static constants.Browser.CHROME;
import static constants.Browser.FIREFOX;
import static constants.FaqDescription.*;
import static constants.FaqDescription.DESCRIPTION_SERVICE_AREA;
import static constants.FaqTitle.*;
import static constants.FaqTitle.TITLE_SERVICE_AREA;

public class ParameterizedTestData {
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

    private static Stream<Arguments> orderTestData() {
        return Stream.of(
                // Проверяем в Chrome
                Arguments.of(CHROME, new Client("Василий", "Петров", "ул Московская", "+79098087766")),
                Arguments.of(CHROME, new Client("Прохор", "Троцкий", "ул Пупинская", "+79996067788")),
                // Проверяем в FireFox
                Arguments.of(FIREFOX, new Client("Василий", "Петров", "ул Московская", "+79098087766")),
                Arguments.of(FIREFOX, new Client("Прохор", "Троцкий", "ул Пупинская", "+79996067788"))
        );
    }

    private static Stream<Arguments> browserTestData() {
        return Stream.of(
                // Проверяем в Chrome
                Arguments.of(CHROME),
                // Проверяем в FireFox
                Arguments.of(FIREFOX)
        );
    }
}
