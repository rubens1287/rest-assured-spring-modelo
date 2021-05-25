package br.com.demo.spring.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseTest {

    private RequestSpecification spec;

    public RequestSpecification getSpec() {
        return spec;
    }

    public void setSpec(RequestSpecification spec) {
        this.spec = spec;
    }

    @Before
    public void SetUp() {

        final String BASE_URL = "http://localhost:8085";

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }
}
