package cars_tests.rest_assured;

import cars.dto.RegistrationDto;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RegistrationTests extends TestBase{
    private final Gson GSON = new Gson();

    @Test
    public void registerNewUserTest() {
        RegistrationDto newUser = RegistrationDto.builder()
                .username("jupiter" + System.currentTimeMillis() + "@gmail.com")
                .password("Improveyourself!7")
                .firstName("Jupiter")
                .lastName("Planet")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .post(regNewUser)
                .then()
                .assertThat()
                .statusCode(200)
                .log().body()
                .body("accessToken", org.hamcrest.Matchers.notNullValue())
        ;
    }


}
