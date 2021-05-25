package br.com.demo.spring.test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import br.com.demo.spring.modelo.Topico;
import io.restassured.response.Response;
import org.junit.Test;

public class TopicoServiceTest extends BaseTest {

    private Topico topico;

    @Test
    public void deveRetornarTodosTopicos() {
        Response response = given()
                .spec(getSpec())
                .contentType("charset=utf-8")
                .when()
                .get("/topicos");

        response.then()
                .contentType("application/json")
                .statusCode(200)
                .body("titulo[0]",equalToIgnoringCase("Duvida"));

        assertThat(response.getBody().prettyPrint(), matchesJsonSchemaInClasspath("Topico-Schema.json"));
    }
}
