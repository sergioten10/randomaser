package api.tests;

import api.responseStructure.Info;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class OldVersionTests extends BaseURL {
    //Проверка работоспособности старых апишек
    @Test
    public void OldAPI3() {
        Info info = given()
                .when()
                .get("1.3/")
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("1.3", info.getVersion());
    }
    @Test
    public void OldAPI2() {
        Info info = given()
                .when()
                .get("1.2/")
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("1.2", info.getVersion());
    }
}
