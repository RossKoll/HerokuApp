package pages;

import lombok.ToString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@ToString
public class AllProductPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "btn-outline-success")
    WebElement addProductButton;




    public AllProductPage(WebDriver driver){
        this.driver = driver;
    }

    public String  getPageUrl(){
        String url = driver.getCurrentUrl();
        return  url;
    }

    public AllProductPage addProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        wait.until(ExpectedConditions.elementToBeClickable(addProductButton)).click();
        return this;
    }

}
