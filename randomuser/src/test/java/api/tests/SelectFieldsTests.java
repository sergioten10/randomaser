package api.tests;

import api.responseStructure.Result;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class SelectFieldsTests extends BaseURL {
    // исключение полей
    @Test
    public void ExcludingFields() {
        Result randomUser = given()
                .when()
                .param("exc", "gender,name")
                .get()
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getList("results", Result.class).get(0);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(randomUser.getGender()).isNull();
            softly.assertThat(randomUser.getName()).isNull();
        });
    }

    // включение полей
    @Test
    public void IncludingFields() {
        Result randomUser = given()
                .when()
                .param("inc", "gender,name")
                .get()
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getList("results", Result.class).get(0);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(randomUser.getGender()).isNotNull();
            softly.assertThat(randomUser.getName()).isNotNull();
        });
    }


    // включение несуществующего поля и возврат контента с пустыми полями
    @Test
    public void IncludingIncorrectFields() {
        Result randomUser = given()
                .when()
                .param("inc", "BankAccount")
                .get()
                .then().assertThat().statusCode(200).log().all()
                .extract().body().jsonPath().getList("results", Result.class).get(0);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(randomUser.getGender()).isNull();
            softly.assertThat(randomUser.getName()).isNull();
            softly.assertThat(randomUser.getLocation()).isNull();
            softly.assertThat(randomUser.getEmail()).isNull();
            softly.assertThat(randomUser.getLogin()).isNull();
            softly.assertThat(randomUser.getRegistered()).isNull();
            softly.assertThat(randomUser.getDob()).isNull();
            softly.assertThat(randomUser.getPhone()).isNull();
            softly.assertThat(randomUser.getCell()).isNull();
            softly.assertThat(randomUser.getId()).isNull();
            softly.assertThat(randomUser.getPicture()).isNull();
            softly.assertThat(randomUser.getNat()).isNull();
        });
    }
}
