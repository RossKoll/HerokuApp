package scenarios;

import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.RegisterPage;


public class Login extends Base {

    private static LoginPage loginPage;
    private static RegisterPage registerPage;

    public Login() {
        super();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();

    }

    @Test
    public void login(){

        //GIVEN
        String email = "fakeEmail@gmail.com";
        String password = "fakePassword";

        //WHEN
        loginPage.fillInInfo(email,password,driver);

        //THEN
        loginPage.checkRegisterPageOpened(wait);
    }

    @Test
    public void register(){
        //GIVEN
        //WHEN
        registerPage.fillInRegisterPage(driver);

        //THEN
        registerPage.checkLoginPageOpened(wait);


    }


}
