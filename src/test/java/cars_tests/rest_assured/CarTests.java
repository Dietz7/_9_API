package cars_tests.rest_assured;

import cars.dto.CarDto;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CarTests extends TestBase {
    private final Gson GSON = new Gson();

    @Test
    public void checkResponseHeadersPositiveTest() {
        given()
                .header(AUTH, TOKEN)
                .get(carsDto)
                .then().assertThat().statusCode(200)
                .log().body()
                .log().headers()
                .contentType(ContentType.JSON)
                .header("Content-Type", equalTo("application/json"))
                .header("Content-Type", containsString("json"))
                .header("X-Xss-Protection", equalTo("1; mode=block"))
        ;

    }

    @Test
    public void addNewCar() {
        CarDto car = CarDto.builder()
                .serialNumber("ABC123456" + System.currentTimeMillis())
                .manufacture("Toyota")
                .model("Corolla")
                .year("2020")
                .fuel("Petrol")
                .seats("5")
                .carClass("C")
                .pricePerDay("55")
                .about("Reliable and economical")
                .city("Haifa")
                .build();

        given()
                .header(AUTH, TOKEN)
                .contentType(ContentType.JSON)
                .body(car)
                .when()
                .post(carDto)
                .then()
                .assertThat()
                .statusCode(200)
                .log().body()
                .body("message", equalTo("Car added successfully"));
    }

}
