package cars_tests.rest_assured;

import cars.dto.AuthRequestDto;
import cars.dto.AuthResponseDto;
import cars.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class LoginRestAssuredTests extends TestBase {
    SoftAssert softAssert = new SoftAssert();
    AuthRequestDto body = AuthRequestDto.builder()
            .username("julitester7@gmail.com")
            .password("Test7!123")
            .build();

    AuthRequestDto errorBody = AuthRequestDto.builder()
            .username("julitester7@gmail.com")
            .password("Test7!123")
            .build();

    @Test
    public void loginSimpleTestWithOutAssert() {
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(loginDto)
                .then()
                .log().all()
        ;
    }

    @Test
    public void loginSuccessPositiveTest1() {
        AuthResponseDto dto = given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(loginDto)
                .then()
                .assertThat()
                .statusCode(200)
                //.log().all()
                .extract().response().as(AuthResponseDto.class);
        System.out.println(dto);

    }

    @Test
    public void loginSuccessPositiveTest2() {
        String responseToken = given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(loginDto)
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("accessToken"))
                //.log().all()
                .extract().path("accessToken");
        System.out.println(responseToken);

    }
    @Test
    public void loginWithWrongPasswordTest(){
        ErrorDto errorDto = given()
                .contentType(ContentType.JSON)
                .body(errorBody)
                .post(loginDto)
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDto.class);
        System.out.println(errorDto);

        int status = errorDto.getStatus();

        System.out.println(status);
        softAssert.assertEquals(status, 401);
        Object message = errorDto.getMessage();
        System.out.println(message);
        softAssert.assertEquals(message, "Login or Password incorrect", "Expected specific error message");
        String error = errorDto.getError();
        System.out.println(error);
        softAssert.assertEquals(error, "Unauthorized", "Expected 'Unauthorized' error description");

        softAssert.assertAll();
    }
}
