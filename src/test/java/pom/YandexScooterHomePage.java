package pom;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

/**
 * Класс заглавной страницы Яндекс Самокат
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class YandexScooterHomePage {
    private final WebDriver driver;
    private String faqSpoilerTitle;
    // Кнопка "Заказать" в блоке "Как это работает"
    private final By orderButton =
            By.xpath("//*[@class='Home_FinishButton__1_cWm']/button");

    /**
     * Вспомогательный метод для получения WebElement выпадающего списка в разделе «Вопросы о важном»
     *
     * @return WebElement
     */
    private WebElement getFaqSpoilerWebElement() {
        return driver.findElement(By.xpath(".//*[text()='" + faqSpoilerTitle + "']"));
    }

    /**
     * Метод нажимает на выпадающий список в разделе «Вопросы о важном»
     */
    public void clickFaqSpoiler() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", getFaqSpoilerWebElement());
        WaitUtils.waitToBeClickable(driver, getFaqSpoilerWebElement());
        getFaqSpoilerWebElement().click();
    }

    /**
     * Метод для получения фактического текста из выпадающего списока в разделе «Вопросы о важном»
     *
     * @return String
     */
    public String getFaqSpoilerDescription() {
        By faqSpoilerDescription =
                with(By.xpath("//*[contains(@id, 'accordion__panel')]")).near(getFaqSpoilerWebElement());
        WaitUtils.waitToBeClickable(driver, getFaqSpoilerWebElement());
        return driver.findElement(faqSpoilerDescription).getText();
    }

    /**
     * Метод нажимает на кнопку "Заказать" в блоке "Как это работает"
     */
    public void clickOrderButton() {
        WebElement webElement = driver.findElement(orderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        WaitUtils.waitToBeClickable(driver, webElement);
        webElement.click();
    }
}