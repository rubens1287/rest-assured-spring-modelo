package br.com.demo.spring.test;

import br.com.demo.spring.modelo.Curso;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CursosServiceTest extends BaseTest{


    @Test
    public void deveRetornaTodosCursos(){

        //Arrange
        //Act
        Response response = given()
                .spec(getSpec())
                .when()
                .get("/curso");
        //Assert
        response.then()
                .statusCode(200)
                .body("id[0]", equalTo(1))
                .body("nome[0]", equalTo("Spring Boot"))
                .body("categoria[0]", equalTo("Programacao"));

        assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("Cursos-All-Schema.json"));
    }

    @Test
    public void deveCriarUmCurso(){

        //Arrange
        Curso curso = Curso.builder()
                .nome("Novo curso")
                .categoria("RestAssured")
                .build();
        //Act
        Response response = given()
                .spec(getSpec())
                .body(curso)
                .when()
                .post("/curso");
        //Assert
        response.then()
                .statusCode(201)
                .body("nome", equalTo(curso.getNome()))
                .body("categoria", equalTo(curso.getCategoria()));
    }





}
