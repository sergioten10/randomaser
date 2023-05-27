package api.tests;

import api.specifications.Specifications;
import org.junit.jupiter.api.BeforeAll;

public class BaseURL {
    protected static final String URL = "https://randomuser.me/api/";
    protected static final String ResponseSchema = "./src/test/resources/ResponseSchema.json";
    @BeforeAll
    protected static void setUp() {
        Specifications.installSpecification(Specifications.requestSpecification(URL));
    }
}
