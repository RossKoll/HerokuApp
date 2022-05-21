package scenarios;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class base {

    protected static WebDriver driver;
    static WebDriverWait wait;

    @BeforeEach
    public void SetUpDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("start-maximized");
        options.setHeadless(false);
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofMillis(3000L));
    }

    @AfterEach
    public void exit() {
        driver.quit();
    }

}
