package com.corpcazorla.location;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testListEpidemiologicalAttentionSuccess() {

        given()
            .queryParam("parentId", 1)

        .when()
            .get("/api/v1/epidemiological-attentions")

        .then()
            .statusCode(200)

            // Validación estructura response
            .body("data", not(empty()))

            // Validación datos
            .body("data[0].consultationDate", notNullValue())
            .body("data[0].codeOA", notNullValue())

            // Validación meta
            .body("meta", notNullValue());
    }

	/*
	 * @Test void testListAdministrativeDivisionInvalidParentId() {
	 * 
	 * given() .queryParam("parentId", "abc")
	 * 
	 * .when() .get("/api/locations/administrative-divisions")
	 * 
	 * .then() .statusCode(400); }
	 */
}