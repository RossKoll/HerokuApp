package scenarios;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Gender;
import models.ResponseModel;
import models.Status;
import models.UserModel;
import net.bytebuddy.utility.RandomString;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTestUsers {

    String BASE_URL = "https://gorest.co.in/public/v1";

    @Test
    @DisplayName("Response 200")
    public void CheckUrlReturn200Code(){

        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/users");

        //response.prettyPrint();
        response.then().statusCode(HttpStatus.SC_OK);
        response.then().body(Matchers.not(Matchers.empty()));
        response.prettyPrint();
        int paginationLimitOnPage = response.jsonPath().getInt("meta.pagination.limit");
        List<Object> usersList = response.jsonPath().getList("data");
        Assertions.assertThat(usersList.size()).isEqualTo(paginationLimitOnPage);
        if (usersList.size() == 20){
            System.out.println("Users list contains 20 user in the first page");
        }
        Object firstUserName = response.jsonPath().get("data[0].name");
        System.out.println("the first user name in the list is : "+firstUserName);
    }

    @Test
    @DisplayName("new user cannot be added without Authorization")
    public void addUserWithoutAuthorization(){

        Response postResponse = given()
                .baseUri(BASE_URL)
                .when()
                .post("/users");

        postResponse.then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void addNewUser_authorized(){
        String email = RandomString.make(6)+"@gmail.com";
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setName("Name Test");
        userModel.setGender(Gender.male);
        userModel.setStatus(Status.active);

        Response postResponse = given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer 6a2e66915f5232398603c71eda843f6076c46a853840ec5046ae6b7190db7f36")
                .header("Content-type", ContentType.JSON)
                .body(userModel)
                .when()
                .post("/users");

        postResponse.prettyPrint();
        postResponse.then().statusCode(HttpStatus.SC_CREATED);

        ResponseModel actualModel = postResponse.body().as(ResponseModel.class);

        // with assertion
        Assertions.assertThat(actualModel.getData().getEmail()).isEqualTo(email);
        Assertions.assertThat(actualModel.getData().getName()).isEqualTo(userModel.getName());
        Assertions.assertThat(actualModel.getData().getGender()).isEqualTo(userModel.getGender());
        Assertions.assertThat(actualModel.getData().getStatus()).isEqualTo(userModel.getStatus());
        System.out.println("All GOOD with Assertion");

        //with matchers
        postResponse.then().body("data", Matchers.hasEntry("email", userModel.getEmail()));
        postResponse.then().body("data", Matchers.hasEntry("name", userModel.getName()));
        postResponse.then().body("data", Matchers.hasEntry("gender", userModel.getGender().toString()));
        postResponse.then().body("data", Matchers.hasEntry("status", userModel.getStatus().toString()));
        System.out.println("All GOOD with matchers");

        String actualEmail = postResponse.jsonPath().getString("data.email");
        Assertions.assertThat(actualEmail).isEqualTo(email);


    }




}
