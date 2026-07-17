package com.corpcazorla.location;

import com.corpcazorla.location.application.model.DataPage;
import com.corpcazorla.location.application.service.EpidemiologicalAttentionService;
import com.corpcazorla.location.domain.model.EpidemiologicalAttention;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class EpidemiologicalAttentionControllerTest {

	@InjectMock
	EpidemiologicalAttentionService service;

	@Test
	void testListAdministrativeDivisionsSuccess() {
		// =========================
		// Arrange
		// =========================

		EpidemiologicalAttention attention = new EpidemiologicalAttention();

		attention.setConsultationDate("2025-06-19");
		attention.setCodeOA("0001251152");
		attention.setMedicalRecordCode("550304");
		attention.setPatientName("BUSTAMANTE FONSECA , LUCAS SANTIAGO");
		attention.setIdentityDocument("93698456");

		DataPage<EpidemiologicalAttention> mockPage = new DataPage<>(List.of(attention), 1);

		when(service.listEpidemiologicalAttention(any())).thenReturn(mockPage);

		// =========================
		// Act + Assert
		// =========================

		given().contentType("application/json").queryParam("parentId", 1)

				.when().get("/api/v1/epidemiological-attentions")

				.then().statusCode(200)

				.body("data[0].consultationDate", is("2025-06-19")).body("data[0].patientName", is("BUSTAMANTE FONSECA , LUCAS SANTIAGO"))
				.body("data[0].codeOA", is("0001251152")).body("data[0].identityDocument", is("93698456"))
				.body("data[0].medicalRecordCode", is("550304"));
	}
}
