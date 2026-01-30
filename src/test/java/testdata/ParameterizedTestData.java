package testdata;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static constants.FaqDescription.*;
import static constants.FaqDescription.DESCRIPTION_SERVICE_AREA;
import static constants.FaqTitle.*;
import static constants.FaqTitle.TITLE_SERVICE_AREA;

public class ParameterizedTestData {
    private static final Client client = new Client();

    private static Stream<Arguments> faqTestData() {
        return Stream.of(
                Arguments.of(TITLE_PRICE, DESCRIPTION_PRICE),
                Arguments.of(TITLE_MULTIPLE_SCOOTERS, DESCRIPTION_MULTIPLE_SCOOTERS),
                Arguments.of(TITLE_RENT_TIME, DESCRIPTION_RENT_TIME),
                Arguments.of(TITLE_ORDER_TODAY, DESCRIPTION_ORDER_TODAY),
                Arguments.of(TITLE_PROLONGATION, DESCRIPTION_PROLONGATION),
                Arguments.of(TITLE_CHARGER, DESCRIPTION_CHARGER),
                Arguments.of(TITLE_CANCEL_ORDER, DESCRIPTION_CANCEL_ORDER),
                Arguments.of(TITLE_SERVICE_AREA, DESCRIPTION_SERVICE_AREA)
        );
    }

    private static Stream<Arguments> orderTestData() {
        return Stream.of(
                Arguments.of(client.toBuilder().firstName("Василий").familyName("Петров").address("ул Московская").phoneNumber("+79098087766").build()),
                Arguments.of(client.toBuilder().firstName("Прохор").familyName("Троцкий").address("ул Пупинская").phoneNumber("+79996067788").build())
        );
    }
}
