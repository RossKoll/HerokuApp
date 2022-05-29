package pages;

import lombok.ToString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
@ToString
public class AllProductPage {
    WebDriver driver;
    WebDriverWait wait;

    public AllProductPage(WebDriver driver){
        this.driver = driver;
    }

    public String  getPageUrl(){
        String url = driver.getCurrentUrl();
        return  url;
    }

}
