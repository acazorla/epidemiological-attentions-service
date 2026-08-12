package com.corpcazorla.epidemiological.infrastructure.adapter.out.persistence.mapper;

import jakarta.enterprise.context.ApplicationScoped;

import com.corpcazorla.epidemiological.domain.model.EpidemiologicalAttention;
import com.corpcazorla.epidemiological.infrastructure.adapter.in.dto.*;

import java.util.Collections;
import java.util.List;


@ApplicationScoped
public class EpidemiologicalAttentionPersistenceMapper {

	// MÉTODO: Convierte Dominio -> DTO de Respuesta
	public List<EpidemiologicalAttentionResponse> toResponseList(List<EpidemiologicalAttention> epidemiologicalAttention) {
		return epidemiologicalAttention.stream().map(this::toResponse).toList();
	}

	public EpidemiologicalAttentionResponse toResponse(EpidemiologicalAttention epidemiologicalAttention) {
		
		EpidemiologicalAttentionResponse dto = new EpidemiologicalAttentionResponse();
		
		dto.setConsultationDate(epidemiologicalAttention.getConsultationDate());
		dto.setCodeOA(epidemiologicalAttention.getCodeOA());
		dto.setMedicalRecordCode(epidemiologicalAttention.getMedicalRecordCode());
		dto.setPatientName(epidemiologicalAttention.getPatientName());
		dto.setIdentityDocument(epidemiologicalAttention.getIdentityDocument());		
		dto.setAge(epidemiologicalAttention.getAge());
		dto.setSex(epidemiologicalAttention.getSex());
		dto.setDiagnosisCode(epidemiologicalAttention.getDiagnosisCode());
		dto.setDiagnosisDescription(epidemiologicalAttention.getDiagnosisDescription());
		dto.setDoctorName(epidemiologicalAttention.getDoctorName());
		dto.setSpecialtyDescription(epidemiologicalAttention.getSpecialtyDescription());
		dto.setDiagnosisType(epidemiologicalAttention.getDiagnosisType());
		dto.setCareType(epidemiologicalAttention.getCareType());
		dto.setBirthDate(epidemiologicalAttention.getBirthDate());
		
		return dto;
	}

    public List<EpidemiologicalAttention> fromRows(List<Object[]> rows) {

        if (rows == null || rows.isEmpty()) {
            return Collections.emptyList();
        }

        return rows.stream()
                .map(this::mapRow)
                .toList();
    }
    private EpidemiologicalAttention mapRow(Object[] row) {

        EpidemiologicalAttention attention = new EpidemiologicalAttention();

        attention.setConsultationDate(getString(row, 0));
        attention.setCodeOA(getString(row, 1));
        attention.setMedicalRecordCode(getString(row, 2));
        attention.setPatientName(getString(row, 3));
        attention.setIdentityDocument(getString(row, 4));
        attention.setAge(getInteger(row, 5));
        attention.setSex(getString(row, 6));
        attention.setDiagnosisCode(getString(row, 7));
        attention.setDiagnosisDescription(getString(row, 8));
        attention.setDoctorName(getString(row, 9));
        attention.setSpecialtyDescription(getString(row, 10));
        attention.setDiagnosisType(getString(row, 11));
        attention.setCareType(getString(row, 12));
        attention.setBirthDate(getString(row, 13));

        return attention;
    }
    // =========================
    // Helpers seguros
    // =========================
    private String getString(Object[] row, int index) {
        return row[index] != null
                ? row[index].toString()
                : null;
    }

    private Integer getInteger(Object[] row, int index) {
        if (row[index] == null) {
            return null;
        }
        if (row[index] instanceof Integer value) {
            return value;
        }
        if (row[index] instanceof Number number) {
            return number.intValue();
        }
        return Integer.parseInt(row[index].toString());
    }

    private Short getShort(Object[] row, int index) {
        if (row[index] == null) {
            return null;
        }
        if (row[index] instanceof Short value) {
            return value;
        }
        if (row[index] instanceof Number number) {
            return number.shortValue();
        }
        return Short.parseShort(row[index].toString());
    }

}
