package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage{

   private WebDriver driver;
    String email = "rosko48@gmail.com";
    String password = "12345";

   @FindBy(name = "name")
    WebElement nameField;
    //By nameField = By.name("name");
    @FindBy(name = "last_Name")
            WebElement lastNameField;
    //By lastNameField = By.name("last_Name");
    @FindBy(name = "email")
            WebElement emailField;
    //By emailField = By.name("email");
    @FindBy(name = "password")
            WebElement passwordField;
    //By passwordFiled = By.name("password");
    @FindBy(className = "btn-primary")
            WebElement registerButton;
    //By registerButton = By.className("btn-primary");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public RegisterPage openRegisterPage(){
        driver.get("http://online-sh.herokuapp.com/registration");
        return this;
    }

    public RegisterPage setName(){
        nameField.sendKeys("FirstName");
        return this;
    }

    public RegisterPage setLastName(){
        lastNameField.sendKeys("LastName");
        return this;
    }

    public RegisterPage setEmailField(){
        emailField.sendKeys(email);
        return this;
    }

    public RegisterPage setPassword(){
        registerButton.sendKeys(password);
        return this;
    }

    public RegisterPage submit(){
        registerButton.click();
        return this;
    }

    public RegisterPage fillInRegisterPage(){
        openRegisterPage();
        setName();
        setLastName();
        setEmailField();
        setPassword();
        return this;
    }

    public void checkLoginPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        Boolean loginPageURL = wait.until(ExpectedConditions.urlContains("login"));
        Assertions.assertTrue(loginPageURL);
    }

}
