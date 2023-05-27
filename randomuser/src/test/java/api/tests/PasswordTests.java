package api.tests;

import api.responseStructure.Result;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordTests extends BaseURL{

    @Test
    public void PasswordSpecialRequest() {
        Result randomUser = given()
                .when()
                .params("password", "upper,lower,special,number,8-10")
                .get()
                .then().assertThat().statusCode(200).log().body()
                .extract().body().jsonPath().getList("results", Result.class).get(0);

        String password = randomUser.getLogin().getPassword();

        assertThat(password.matches("^.{8,10}$")).isTrue();
    }
}
