import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CheckboxAndRadioButton {


    WebDriver driver;

    @BeforeEach
    public void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe" );

        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        options.addArguments("start-maximized");
        options.setHeadless(false);
        driver = new ChromeDriver(options);
    }

    @Test
    public void clickOnCheckbox() {
        //GIVEN
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
        WebElement defaultCheckbox = driver.findElement(By.id("flexCheckDefault"));
        
        //scroll to checkbox
        scrollToElement("window.scrollBy(0,250)");

        //WHEN
        if(!defaultCheckbox.isSelected()){
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", defaultCheckbox);

        }else {
            //unselect checkbox
            defaultCheckbox.click();
            //select it again
            defaultCheckbox.click();
        }
        //THEN
        Assertions.assertTrue(defaultCheckbox.isSelected());

    }

    @Test
    public void clickOnSecondCheckbox(){
        //GIVEN
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");

        Actions actions = new Actions(driver);
        WebElement checkedCheckbox = driver.findElement(By.id("flexCheckChecked"));

        //scroll to checkbox
        scrollToElement("window.scrollBy(0,250)");

        //WHEN
        if(!checkedCheckbox.isSelected()){
            clickOnElement(actions,checkedCheckbox);
        }else {
            //unselect checkbox
            clickOnElement(actions, checkedCheckbox);

            //select it again
            clickOnElement(actions, checkedCheckbox);
        }

        //THEN
        Assertions.assertTrue(checkedCheckbox.isSelected());
    }

    @Test
    public void checkIfRadioButtonSelected(){
        //GIVEN
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
        scrollToElement("window.scrollBy(0,1950)");

        //WHEN
        WebElement flexRadioDefault1 = driver.findElement(By.id("flexRadioDefault1"));

        //THEN
        Assertions.assertFalse(flexRadioDefault1.isSelected());
    }

    @Test
    public void selectRadioButton() throws InterruptedException {
        //GIVEN
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
        scrollToElement("window.scrollBy(0,1950)");

        //WHEN
        WebElement radioButton = driver.findElement(By.className("form-check-input"));
        clickOnElementJs(radioButton);

        //THEN
        Assertions.assertTrue(radioButton.isSelected());
    }



    @Test
    public void checkRadioButtonIsDisabled(){
        //GIVE
        driver.get("https://getbootstrap.com/docs/5.0/forms/checks-radios/");
        scrollToElement("window.scrollBy(0,2550)");

        //WHEN
        WebElement radioButtonDisabled = driver.findElement(By.xpath("//input[@id='flexRadioDisabled']"));

        String radioButtonStatus = radioButtonDisabled.getAttribute("disabled");
        Boolean isRadioButtonDisabled = Boolean.parseBoolean(radioButtonStatus);
        //THEN
        Assertions.assertTrue(isRadioButtonDisabled);

    }



    private void clickOnElementJs(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    private void scrollToElement(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, "");
    }


    private void clickOnElement(Actions actions, WebElement element) {
        actions.moveToElement(element).click();
    }

    @AfterEach
    public void close(){
        driver.quit();
    }
}
