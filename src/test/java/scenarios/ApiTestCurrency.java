package scenarios;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTestCurrency {

    private String BASE_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory";

    @Test
    @DisplayName(" Check Currency list is not Empty")
    public void checkCurrencyListIsNotEmpty(){
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/exchange?json");
        response.equals(HttpStatus.SC_OK);
        response.then()
                .log().ifValidationFails()
                .body("$s", notNullValue());


    }
    @Test
    @DisplayName("Check Currency list contain 'USD'")
    public void checkCurrencyListContainUSD(){

        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/exchange?json");
        //response.prettyPrint();
        response.equals(HttpStatus.SC_OK);
        response.then()
                .log().ifValidationFails()
                .body("cc", hasItem("USD"));
    }
}
