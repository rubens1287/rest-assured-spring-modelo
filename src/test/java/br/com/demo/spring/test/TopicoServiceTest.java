package br.com.demo.spring.test;

import br.com.demo.spring.modelo.Topico;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class TopicoServiceTest extends BaseTest {
    
/*  given().
        params("firstName", "John", "lastName", "Doe").
    when().
        post("/greetMe").
    then().
        body(hasXPath("/greeting/firstName[text()='John']"));*/

    @Test
    public void deveRetornarTodosTopicos() {
        //Arrange
        //Act
        Response response = given()
                .spec(getSpec())
                .contentType("charset=utf-8")
                .when()
                .get("/topicos");
        //Assert
        response.then()
                .contentType("application/json")
                .statusCode(200);

        assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("Topico-All-Schema.json"));
    }

    @Test
    public void deveCriarUmTopico() {
        //Arrange
        Topico topico = Topico.builder()
                .titulo("Teste")
                .nomeAutor("Aluno")
                .mensagem("Nova mensagem")
                .emailUsuario("aluno@email.com")
                .nomeCurso("Spring Boot").build();
        //Act
        Response response = given()
                .spec(getSpec())
                .when()
                .body(topico)
                .post("/topicos");
        //Assert
        response.then()
                .contentType("application/json")
                .statusCode(201)
                .body("titulo", equalTo(topico.getTitulo()))
                .body("mensagem",equalTo(topico.getMensagem()));
        assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("Topico-Schema.json"));
    }

    @Test
    public void deveAtualizarUmTopico() {
        //Arrange
        Topico topico = Topico.builder()
                .titulo("Atualizar Titulo")
                .nomeAutor("Aluno")
                .mensagem("Nova mensagem")
                .emailUsuario("aluno@email.com")
                .nomeCurso("Spring Boot").build();
        //Act
        Response response = given()
                .spec(getSpec())
                .when()
                .body(topico)
                .put("/topicos/2");
        //Assert
        response.then()
                .contentType("application/json")
                .statusCode(200)
                .body("titulo", equalTo(topico.getTitulo()))
                .body("mensagem",equalTo(topico.getMensagem()));
        assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("Topico-Schema.json"));
    }

    @Test
    public void deveDeletarUmTopico() {
        //Arrange
        Response response;
        Topico topPay = Topico.builder()
                .titulo("Teste")
                .nomeAutor("Aluno")
                .mensagem("Nova mensagem")
                .emailUsuario("aluno@email.com")
                .nomeCurso("Spring Boot").build();
        response = given()
                .spec(getSpec())
                .when()
                .body(topPay)
                .post("/topicos");
        response.then()
                .contentType("application/json")
                .statusCode(201)
                .body("titulo", equalTo(topPay.getTitulo()))
                .body("mensagem",equalTo(topPay.getMensagem()));
        Topico newTopico = response.as(Topico.class, ObjectMapperType.GSON);
        //Act
        response = given()
                .spec(getSpec())
                .when()
                .body(topPay)
                .delete("/topicos/".concat(String.valueOf(newTopico.getId())));
        //Assert
        response.then()
                .statusCode(200);

    }

}
