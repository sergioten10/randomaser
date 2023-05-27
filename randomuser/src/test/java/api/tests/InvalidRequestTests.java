package api.tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class InvalidRequestTests extends BaseURL{
    /*
    тест на неправильный тип запроса
     */
    @Test
    public void SendPost() {
        given()
                .when()
                .post()
                .then().assertThat().statusCode(404).log().all();
    }
    @Test
    public void SendPut() {
        given()
                .when()
                .put()
                .then().assertThat().statusCode(404).log().all();
    }
    @Test
    public void SendDelete(){
        given()
                .when()
                .delete()
                .then().assertThat().statusCode(404).log().all();
    }
}
