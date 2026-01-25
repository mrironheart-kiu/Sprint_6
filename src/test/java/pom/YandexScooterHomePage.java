package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

/**
 * Класс заглавной страницы Яндекс Самокат
 */
public class YandexScooterHomePage {
    private final WebDriver driver;
    private final String faqSpoilerTitle;

    public YandexScooterHomePage(WebDriver driver, String faqSpoilerTitle) {
        this.driver = driver;
        this.faqSpoilerTitle = faqSpoilerTitle;
    }

    /**
     * Вспомогательный метод для получения WebElement выпадающего списка в разделе «Вопросы о важном»
     * @return WebElement
     */
    private WebElement getFaqSpoilerWebElement(){
        return driver.findElement(By.xpath(".//*[text()='" + faqSpoilerTitle + "']"));
    }

    /**
     * Метод нажимает на выпадающий список в разделе «Вопросы о важном»
     */
    public void clickFaqSpoiler() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", getFaqSpoilerWebElement());
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(getFaqSpoilerWebElement()));
        getFaqSpoilerWebElement().click();
    }

    /**
     * Метод для получения фактического текста из выпадающего списока в разделе «Вопросы о важном»
     * @return String
     */
    public String getFaqSpoilerDescription() {
        By faqSpoilerDescription =
                with(By.xpath("//*[contains(@id, 'accordion__panel')]")).near(getFaqSpoilerWebElement());
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(faqSpoilerDescription));
        return driver.findElement(faqSpoilerDescription).getText();
    }
}