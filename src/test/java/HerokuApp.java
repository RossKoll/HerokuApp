//import org.asynchttpclient.util.DateUtils;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.util.List;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class HerokuApp {
//
//    WebDriver driver;
//    String email = "rosko48@gmail.com";
//    String password = "12345";
//    LocalDate date = LocalDate.now();
//
//
//    @BeforeEach
//    public void SetUpDriver(){
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe" );
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("incognito");
//        options.addArguments("start-maximized");
//        options.setHeadless(false);
//        driver = new ChromeDriver(options);
//    }
//
//
//    @Test()
//    @Order(1)
//    public void SignIn(){
//
//        //GIVEN
//        String expectedUrl = "http://online-sh.herokuapp.com/registration";
//
//        //WHEN
//        driver.get("https://online-sh.herokuapp.com/login");
//        driver.findElement(By.id("exampleInputEmail1")).sendKeys("SomeEmail@gmail.com");
//        driver.findElement(By.id("exampleInputPassword1")).sendKeys(password);
//        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
//        String actualInfoPageUrl = driver.getCurrentUrl();
//
//        //THEN
//        Assertions.assertEquals(expectedUrl, actualInfoPageUrl, "Wrong page opened");
//
//    }
//
//    @Test
//    @Order(2)
//    public void RegistrationPage(){
//
//        //GIVEN
//        String expectedLoginPage = "http://online-sh.herokuapp.com/login";
//        driver.get("http://online-sh.herokuapp.com/registration");
//
//        //WHEN
//        fillInUserInfo();
//        String actualLoginPage = driver.getCurrentUrl();
//
//        //THEN
//        Assertions.assertEquals(expectedLoginPage,actualLoginPage, "Different page is opened" + actualLoginPage);
//    }
//
//
//
//
//    @Test
//    @Order(3)
//    public void LoginAsRegisterUser(){
//
//        //GIVEN
//        String expectedPage = "http://online-sh.herokuapp.com/products";
//
//        //WHEN
//        fillInLoginPage();
//        String actualPAge = driver.getCurrentUrl();
//
//        //THEN
//        Assertions.assertEquals(expectedPage, actualPAge, "Wrong page opened");
//    }
//
//
//    @Test
//    @Order(4)
//    public void addProduct(){
//        //GIVEN
//        fillInLoginPage();
//        addNewProduct(date);
//
//        //WHEN
//        WebElement addedProduct = driver.findElement(By.xpath("//td[starts-with(text(),'" + date + "')]"));
//
//        //THEN
//        Assertions.assertNotNull(addedProduct, "Newly product cannot be found");
//
//    }
//
//    @Test
//    @Order(5)
//    public void updateProduct(){
//        //GIVEN
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
//        fillInLoginPage();
//        addProduct();
//        WebElement updateButtonOfAddedProduct = driver.findElement(By.xpath("//td[contains(text(),'"+ date +"')]/following::td/div/form/child::button[contains(text(),'Update')]"));
//        wait.until(ExpectedConditions.elementToBeClickable(updateButtonOfAddedProduct));
//        updateButtonOfAddedProduct.click();
//        String productName = driver.findElement(By.xpath("//input[@id='exampleInputProduct1']")).getText();
//        driver.findElement(By.xpath("//input[@id='exampleInputProduct1']")).sendKeys(productName + " Edit");
//        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
//        WebElement editedElement = driver.findElement(By.xpath("//td[contains(text(),'Edit')]"));
//
//        //THEN
//        Assertions.assertNotNull(editedElement, "Element with Edit text in name is not found");
//    }
//
//    @Test
//    @Order(6)
//    public void deleteElement(){
//        //GIVEN
//        boolean actualResult = false;
//        fillInLoginPage();
//        int initialSize = listElementsSize();
//
//        //WHEN
//        deleteElement(initialSize);
//
//        int sizeAfterDelete = listElementsSize();
//        if (sizeAfterDelete < initialSize || sizeAfterDelete == 0){
//            actualResult = true;
//        }
//
//        //THEN
//        Assertions.assertTrue(actualResult);
//    }
//
//
//    private void deleteElement(int initialSize) {
//        if (initialSize > 0){
//            clickDeleteButton();
//        }else {
//            addProduct();
//            clickDeleteButton();
//        }
//    }
//
//
//    @AfterEach
//    public void Close(){
//        driver.quit();
//    }
//
//    public void fillInLoginPage(){
//        driver.get("https://online-sh.herokuapp.com/login");
//        driver.findElement(By.id("exampleInputEmail1")).sendKeys(email);
//        driver.findElement(By.id("exampleInputPassword1")).sendKeys(password);
//        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
//
//    }
//
//    private void fillInUserInfo() {
//
//        driver.findElement(By.name("name")).sendKeys("FirstName");
//        driver.findElement(By.name("last_Name")).sendKeys("LastName");
//        driver.findElement(By.name("email")).sendKeys(email);
//        driver.findElement(By.name("password")).sendKeys(password);
//        driver.findElement(By.className("btn-primary")).click();
//    }
//
//    private void addNewProduct(LocalDate date) {
//        driver.findElement(By.className("btn-outline-success")).click();
//        driver.findElement(By.id("exampleInputProduct1")).sendKeys("Product" + date);
//        driver.findElement(By.id("exampleInputPrice1")).sendKeys("9.99");
//        driver.findElement(By.className("btn-outline-success")).click();
//    }
//
//    private int listElementsSize(){
//        int countElements;
//        List<WebElement> elementList = driver.findElements(By.xpath("//tbody/tr"));
//        return countElements = elementList.size();
//    }
//
//    public void clickDeleteButton(){
//        driver.findElement(By.xpath("(//button[@type='submit'][normalize-space()='Delete'])[1]")).click();
//    }
//
//}
