package adapters;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    public static final String BASE_API_URL = System.getenv().getOrDefault("apiURL", PropertyReader.getProperty("apiURL"));
    RequestSpecification request;

    public BaseAdapter() {
        request = given().
                contentType(ContentType.JSON).
                header("Token", System.getenv().getOrDefault("token", PropertyReader.getProperty("token"))).
                log().all();
    }
}
