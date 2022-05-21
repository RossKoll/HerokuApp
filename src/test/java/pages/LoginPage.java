package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage{



    By emailField = By.id("exampleInputEmail1");
    By passwordField = By.id("exampleInputPassword1");
    By submitButton = By.xpath("//button[contains(text(),'Submit')]");

    public LoginPage(){
    }

    public LoginPage openLoginPage(WebDriver driver){
        driver.get("https://online-sh.herokuapp.com/login");
        return this;
    }

    public LoginPage setEmail(WebDriver driver, String email){
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPage setPassword(WebDriver driver, String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginPage submit(WebDriver driver){
        driver.findElement(submitButton).click();
        return this;
    }

    public void checkRegisterPageOpened(WebDriverWait wait) {
        Boolean registrationPageURL = wait.until(ExpectedConditions.urlContains("registration"));
        Assertions.assertTrue(registrationPageURL);
    }

}
