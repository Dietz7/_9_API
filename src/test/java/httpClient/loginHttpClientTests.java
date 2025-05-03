package httpClient;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class loginHttpClientTests {

    private final String LOGIN_URL = "https://ilcarro-backend.herokuapp.com/v1/user/login/usernamepassword";
    private final Gson GSON = new Gson();

    @Test

    public void LoginSuccessTest() throws IOException {
        String jsonBody = """
                {
                    "username": "julitester7@gmail.com",
                        "password": "Test7!123"
                }
                 """;
        Response response = Request.Post(LOGIN_URL)
                .bodyString(jsonBody, ContentType.APPLICATION_JSON).execute();
        System.out.println("we get an object " + response);

        String responseJson = response.returnContent().asString();
        System.out.println(responseJson);

        JsonElement element = JsonParser.parseString(responseJson);
        System.out.println(element);

        JsonElement token = element.getAsJsonObject().get("accessToken");
        System.out.println("Extracted token " + token);

        Assert.assertNotNull(token);

    }

}
