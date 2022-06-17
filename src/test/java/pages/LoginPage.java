package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage{


    private WebDriver driver;
    private String registerEmail = "rosko48@gmail.com";
    private String registerPassword = "12345";

    @FindBy(id = "exampleInputEmail1")
         private WebElement emailField;
    //By emailField = By.id("exampleInputEmail1");

    @FindBy (id = "exampleInputPassword1")
        private WebElement passwordField;
    //By passwordField = By.id("exampleInputPassword1");

    @FindBy(xpath = "//button[contains(text(),'Submit')]")
        private WebElement submitButton;
    //By submitButton = By.xpath("//button[contains(text(),'Submit')]");

    public LoginPage(WebDriver driver){
        this.driver =  driver;

    }

    @Step("Open Login page")
    public LoginPage openLoginPage(){
        driver.get("https://online-sh.herokuapp.com/login");
        return this;
    }

    @Step("Set email as {email}")
    public LoginPage setEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    @Step("Set password as {password}")
    public LoginPage setPassword( String password){
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Click on Submit button")
    public LoginPage submit(){
        submitButton.click();
        return this;
    }

    public void fillInInfo(String email, String password){
        openLoginPage();
        setEmail(email);
        setPassword(password);
        submit();
    }

    public void checkRegisterPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        Boolean registrationPageURL = wait.until(ExpectedConditions.urlContains("registration"));
        Assertions.assertTrue(registrationPageURL);
    }

    public void loginAsRegisterUser(){
        openLoginPage()
                .setEmail(registerEmail)
                .setPassword(registerPassword)
                .submit();
    }

}
