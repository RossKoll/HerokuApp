package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductPage {

    @FindBy(id = "exampleInputProduct1")
    WebElement productNameField;

    @FindBy(id = "exampleInputPrice1")
    WebElement productPriceField;

    @FindBy(className = "btn-outline-success")
    WebElement submitButton;


    @Step("Fill in product name as {name}")
    private ProductPage fillInProductName(String name){
        productNameField.sendKeys(name);
        return  this;
    }

    @Step("Fill in price filed")
    private ProductPage fillInPriceField(){
        productPriceField.sendKeys("9.99");
        return this;
    }

    @Step("Click on Submit button")
    private void submit(){
        submitButton.click();
    }

    public void addNewProduct(String name){
        fillInProductName(name).fillInPriceField().submit();
    }
}
