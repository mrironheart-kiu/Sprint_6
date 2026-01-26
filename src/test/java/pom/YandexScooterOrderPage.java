package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Класс страницы формирования заказа Яндекс Самокат
 */
public class YandexScooterOrderPage {
    private final WebDriver driver;
    // Поле "Имя" на первой форме заказа
    private final By firstNameField =
            By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Имя')]");
    // Поле "Фамилия" на первой форме заказа
    private final By familyNameField =
            By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Фамилия')]");
    // Поле "Адрес" на первой форме заказа
    private final By addressField =
            By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Адрес')]");
    // Поле "Станция метро" на первой форме заказа
    private final By metroStationField =
            By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'метро')]");
    // Первая станция в выпадающем списке "Станция метро"
    private final By metroStation =
            By.xpath("//*[@class='select-search__select']//button[@value='1']");
    // Поле "Телефон" на первой форме заказа
    private final By phoneNumberField =
            By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Телефон')]");
    // Кнопка "Далее" на первой форме заказа
    private final By nextButton =
            By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");
    // Поле "Когда привезти самокат" на второй форме заказа
    private final By orderDateField =
            By.xpath("//*[@class='Order_Form__17u6u']//input[contains(@placeholder, 'Когда привезти самокат')]");
    // Текущая дата в выпадающем календаре поля "Когда привезти самокат" на второй форме заказа
    private final By orderDate =
            By.xpath("//*[contains(@class,'react-datepicker__day--today')]");
    // Поле "Срок аренды" на второй форме заказа
    private final By rentTimeField = By.className("Dropdown-placeholder");
    // Период "сутки" в выпадающем меню "Срок аренды" на второй форме заказа
    private final By rentTime =
            By.xpath("//*[@class='Dropdown-option' and contains(text(),'сутки')]");
    // Кнопка "Заказать" на второй форме заказа
    private final By orderButton =
            By.xpath("//*[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    // Кнопка "Да" на всплывающем окне "Хотите оформить заказ?"
    private final By confirmButton =
            By.xpath("//*[@class='Order_Modal__YZ-d3']//button[contains(text(),'Да')]");
    // Заголовок всплывающего окна об успешном формировании заказа
    private final By completeOrderFormTitle = By.className("Order_ModalHeader__3FDaJ");

    public YandexScooterOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Вспомогательный методы для заполнения значений полей на странце заказа Яндекс Самокат
     * @param fieldName селектор для веб-элемента
     */
    private void checkAndClearField(By fieldName){
        driver.findElement(fieldName).isEnabled();
        driver.findElement(fieldName).clear();
    }

    /**
     * Метод записывает в поле "Имя" переданный параметр
     * @param newFirstName новое значение для заполнения поля
     */
    public void setFirstName(String newFirstName) {
        checkAndClearField(firstNameField);
        driver.findElement(firstNameField).sendKeys(newFirstName);
    }

    /**
     * Метод записывает в поле "Фамилия" переданный параметр
     * @param newFamilyName новое значение для заполнения поля
     */
    public void setFamilyName(String newFamilyName) {
        checkAndClearField(familyNameField);
        driver.findElement(familyNameField).sendKeys(newFamilyName);
    }

    /**
     * Метод записывает в поле "Фамилия" переданный параметр
     * @param newAddress новое значение для заполнения поля
     */
    public void setAddress(String newAddress) {
        checkAndClearField(addressField);
        driver.findElement(addressField).sendKeys(newAddress);
    }

    /**
     * Метод выбирает в поле "Станция метро" станцию
     */
    public void setMetroStation() {
        driver.findElement(metroStationField).click();
        driver.findElement(metroStation).click();
    }

    /**
     * Метод записывает в поле "Телефон" переданный параметр
     * @param newPhoneNumber новое значение для заполнения поля
     */
    public void setPhoneNumber(String newPhoneNumber) {
        checkAndClearField(phoneNumberField);
        driver.findElement(phoneNumberField).sendKeys(newPhoneNumber);
    }

    /**
     * Метод нажимает кнопку "Далее" на первой форме заказа
     */
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    /**
     * Метод выбирает в поле "Когда привезти самокат" текущую дату
     */
    public void setOrderDateField() {
        driver.findElement(orderDateField).click();
        driver.findElement(orderDate).click();
    }

    /**
     * Метод выбирает в поле "Срок аренды" "сутки"
     */
    public void setRentTimeFieldField() {
        driver.findElement(rentTimeField).click();
        driver.findElement(rentTime).click();
    }

    /**
     * Метод нажимает кнопку "Заказать" на второй форме заказа
     */
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    /**
     * Метод нажимает кнопку "Да" на всплывающем окне "Хотите оформить заказ?"
     */
    public void clickConfirmButton() {
        driver.findElement(confirmButton).click();
    }

    /**
     * Метод возвращает текст аголовока всплывающего окна об успешном формировании заказа
     */
    public String getCompleteOrderTitleText() {
        return driver.findElement(completeOrderFormTitle).getText();
    }


    /**
     * Метод формирует заказ самоката через кнопку "Заказать" в хедере страницы
     * @param newFirstName новое значение для заполнения поля "Имя"
     * @param newFamilyName новое значение для заполнения поля "Фамилия"
     * @param newAddress новое значение для заполнения поля "Адресс"
     * @param newPhoneNumber новое значение для заполнения поля "Номер телефона"
     */
    public void createOrderViaHeaderOrderButton(
            String newFirstName, String newFamilyName,
            String newAddress, String newPhoneNumber
            ) {
        setFirstName(newFirstName);
        setFamilyName(newFamilyName);
        setAddress(newAddress);
        setMetroStation();
        setPhoneNumber(newPhoneNumber);
        clickNextButton();
        setOrderDateField();
        setRentTimeFieldField();
        clickOrderButton();
        clickConfirmButton();
    }
}
