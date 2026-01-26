package factory;

import constants.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public WebDriver getWebDriver(Browser browserName){
        switch (browserName){
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            default:
                throw new RuntimeException("Incorrect BrowserName");
        }
    }
}
