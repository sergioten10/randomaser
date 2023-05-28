package api.tests;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class RandomUserTests extends BaseURL {
    /*
    базовая проверка на ответ от сервера +
     */
    @Test
    public void RandomUser() {
        JsonPath response = given()
                .when()
                .get()
                .then().log().all()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(ResponseSchema)))
                .assertThat().statusCode(200)
                .extract().body().jsonPath();

    }
}
