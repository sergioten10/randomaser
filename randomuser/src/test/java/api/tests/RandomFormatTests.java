package api.tests;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
//JSON (default)
//PrettyJSON or pretty
//CSV
//YAML
//XML
// Проверка получения контента в указанном формате
public class RandomFormatTests extends BaseURL {
    @Test
    public void SpecifiedFormatJson() {
        Response response = given()
                .when()
                .param("format", "json")
                .get();

        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualTo("application/json; charset=utf-8");
        int statusCode = response.statusCode();
        assertThat(statusCode).isEqualTo(200);
    }

    @Test
    public void SpecifiedFormatPretty() {
        Response response = given()
                .when()
                .param("format", "pretty")
                .get();

        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualTo("application/json; charset=utf-8");
        int statusCode = response.statusCode();
        assertThat(statusCode).isEqualTo(200);
    }

    @Test
    public void SpecifiedFormatYAML() {
        Response response = given()
                .when()
                .param("format", "yaml")
                .get();

        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualTo("text/x-yaml; charset=utf-8");
        int statusCode = response.statusCode();
        assertThat(statusCode).isEqualTo(200);
    }
    @Test
    public void SpecifiedFormatCSV() {
        Response response = given()
                .when()
                .param("format", "csv")
                .get();

        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualTo("text/csv; charset=utf-8");
        int statusCode = response.statusCode();
        assertThat(statusCode).isEqualTo(200);
    }
    @Test
    public void SpecifiedFormatXML() {
        Response response = given()
                .when()
                .param("format", "xml")
                .get();

        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualTo("text/xml; charset=utf-8");
        int statusCode = response.statusCode();
        assertThat(statusCode).isEqualTo(200);
    }
    //Проверка игнора невалидного формата и возврата JSON

    @Test
    public void SpecifiedFormatInvalid() {
        Response response = given()
                .when()
                .param("format", "гыыы")
                .get();

        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualTo("application/json; charset=utf-8");
        int statusCode = response.statusCode();
        assertThat(statusCode).isEqualTo(200);
    }

}
