package api.tests;

import api.responseStructure.Result;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomGenderTests extends BaseURL {
    /*
    проверка на запрос с указанным гендером
     */
    @ParameterizedTest
    @ValueSource(strings = {"male", "female"})
    public void RandomUserWithSpecifiedGender(String specifiedGender) {
        Result randomUser = given()
                .when()
                .params("gender", specifiedGender)
                .get()
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getList("results", Result.class).get(0);
                assertThat(randomUser.getGender()).isEqualTo(specifiedGender);
    }

    /*
    проверка на игнор гендера ,которого нет в спеке
     */
    @ParameterizedTest
    @ValueSource(strings = {"halfmale", "halffemale"})
    public void RandomUserWithInvalidGender(String specifiedGender) {
        Result randomUser = given()
                .when()
                .params("gender", specifiedGender)
                .get()
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getList("results", Result.class).get(0);
        assertThat(randomUser.getGender()).isNotEqualTo(specifiedGender);
 }
}