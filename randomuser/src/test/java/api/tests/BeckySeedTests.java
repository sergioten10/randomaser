package api.tests;

import api.responseStructure.Result;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.util.List;

import static api.tests.BaseURL.ResponseSchema;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BeckySeedTests extends BaseURL{
    @Test
    public void BeckySeed() {
        List<Result> resultList = given()
                .when()
                .param("seed","foobar")
                .get("1.0/")
                .then().log().all()
                .extract().body().jsonPath().getList("results",Result.class);
    }
}
