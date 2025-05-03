package cars_tests.rest_assured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public final String AUTH = "Authorization";
    public final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoianVsaXRlc3RlcjdAZ21haWwuY29tIiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE3NDY4OTczMDUsImlhdCI6MTc0NjI5NzMwNX0.Y9cfYrpPwLiIrIC1Lgd_0MwMOhcu5J_gnzOhOqU60ZU";

    public final String regNewUser = "/user/registration/usernamepassword";
    public final String loginDto = "/user/login/usernamepassword";

    public final String carsDto = "/cars/my";
    public final String carDto = "/cars";

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://ilcarro-backend.herokuapp.com";
        RestAssured.basePath = "v1";

    }

}
