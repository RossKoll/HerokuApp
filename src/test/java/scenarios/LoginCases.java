package scenarios;

import base.Base;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.support.PageFactory;
import pages.AllProductPage;
import pages.LoginPage;
import pages.RegisterPage;

import static base.Base.driver;

@ExtendWith(ScreenshotTestWatcher.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Feature("Login functionality")
public class LoginCases {

    private static LoginPage loginPage;

    private static RegisterPage registerPage;
    private static AllProductPage allProductPage;


    @BeforeAll
    @Step("Set up driver before test")
    static void init() {
        Base baseSetUp = new Base();
        loginPage = PageFactory.initElements(Base.driver, LoginPage.class);
        registerPage = PageFactory.initElements(Base.driver, RegisterPage.class);
        allProductPage = PageFactory.initElements(Base.driver, AllProductPage.class);
    }

    @RegisterExtension
    ScreenshotOnFailure watcher = new ScreenshotOnFailure(Base.driver, "target/surefire-reports");

    @Test
    @Order(1)
    @DisplayName("login as new user")
    @Step("Login as new user")
    @Issue("6545")
    @Description("Login successful with valid credential")
    public void login() {

        //GIVEN

        String email = "fakeEmail@gmail.com";
        String password = "fakePassword";

        //WHEN
        loginPage
                .openLoginPage()
                .setEmail(email)
                .setPassword(password)
                .submit();

        //THEN
        loginPage.checkRegisterPageOpened();
    }

    @Test
    @Order(2)
    @DisplayName("login as existed user")
    public void loginAsRegisterUser(){
        //GIVEN
        String expectedPage = "http://online-sh.herokuapp.com/products";
        String existedUserEmail = "rosko48@gmail.com";
        String existedUserPassword = "12345";

        //WHEN
        loginPage.openLoginPage()
                .setEmail(existedUserEmail)
                .setPassword(existedUserPassword)
                .submit();
        //THEN
        Assertions.assertEquals(expectedPage, allProductPage.getPageUrl(), "Wrong page opened :" + driver.getCurrentUrl());
    }

    @Test
    @Order(3)
    @DisplayName("register new user")
    public void register() {
        //GIVEN
//        RegisterPage registerPage = new RegisterPage(Base.driver);
        //WHEN
        registerPage
                .openRegisterPage()
                .setName()
                .setLastName()
                .setEmailField()
                .setPassword()
                .submit();
        //THEN
        registerPage.checkLoginPageOpened();
    }

    @AfterAll
    public static void exit() {
        driver.quit();
    }

}

