import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class base {

    WebDriver driver;

    @BeforeClass
    public void SetUpDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe" );

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("start-maximized");
        options.setHeadless(false);
        driver = new ChromeDriver(options);
    }

}
