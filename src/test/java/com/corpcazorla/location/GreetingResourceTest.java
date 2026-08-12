package com.corpcazorla.location;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testListEpidemiologicalAttentionSuccess() {
		String jsonRequestBody = """
				{
					"startDate": "2025-01-01",
					"endDate": "2025-01-06"
				}
				""";
        given()
            .contentType("application/json").body(jsonRequestBody)
        .when()
            .post("/api/v1/epidemiological-attentions/search")

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

	
	  @Test void testListEpidemiologicalAttentionInvalid() {
	  		String jsonRequestBody = """
				{
					"endDate": "2025-01-06"
				}
				""";
	  given() .contentType("application/json").body(jsonRequestBody)
	  
	  .when() .post("/api/locations/administrative-divisions/search")
	  
	  .then() .statusCode(400); }
	 
}