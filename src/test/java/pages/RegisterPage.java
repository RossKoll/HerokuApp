package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage{




    By nameField = By.name("name");
    By lastNameField = By.name("last_Name");
    By emailField = By.name("email");
    By passwordFiled = By.name("password");
    By registerButton = By.className("btn-primary");

    public RegisterPage openRegisterPage(WebDriver driver){
        driver.get("http://online-sh.herokuapp.com/registration");
        return this;
    }

    public RegisterPage setName(WebDriver driver){
        driver.findElement(nameField).sendKeys("FirstName");
        return this;
    }

    public RegisterPage setLastName(WebDriver driver){
        driver.findElement(lastNameField).sendKeys("LastName");
        return this;
    }

    public RegisterPage setEmailField(WebDriver driver){
        driver.findElement(emailField);
        return this;
    }

    public RegisterPage setPassword(WebDriver driver){
        driver.findElement(registerButton).click();
        return this;
    }

    public RegisterPage fillInRegisterPage(WebDriver driver){
        openRegisterPage(driver);
        setName(driver);
        setLastName(driver);
        setEmailField(driver);
        setPassword(driver);
        return this;
    }

    public void checkLoginPageOpened(WebDriverWait wait) {
        Boolean loginPageURL = wait.until(ExpectedConditions.urlContains("login"));
        Assertions.assertTrue(loginPageURL);
    }

}
