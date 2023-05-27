package api.tests;

import api.responseStructure.Result;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomNatTests extends BaseURL {
    /*
    Проверка запроса с указанным гендером
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "AU",
            "BR",
            "CA",
            "CH",
            "DE",
            "DK",
            "ES",
            "FI",
            "FR",
            "GB",
            "IE",
            "IN",
            "IR",
            "MX",
            "NL",
            "NO",
            "NZ",
            "RS",
            "TR",
            "UA",
            "US"})
    public void RandomUserWithSpecifiedNat(String specifiedNationality) {
        Result randomUser = given()
                .when()
                .params("nat", specifiedNationality)
                .get()
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getList("results", Result.class).get(0);

        assertThat(randomUser.getNat()).isEqualTo(specifiedNationality);
    }
    /*
       Проверка на игнор указаного запроса которого не в спеке
        */
    @ParameterizedTest
    @ValueSource(strings = {
            "RU",
            "KZ",
            "KR",
            "UZ",
            "AI",
           })
    public void RandomUserWithInvalidNat(String specifiedNationality) {
        Result randomUser = given()
                .when()
                .params("nat", specifiedNationality)
                .get()
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getList("results", Result.class).get(0);

        assertThat(randomUser.getNat()).isNotEqualTo(specifiedNationality);
    }
}

