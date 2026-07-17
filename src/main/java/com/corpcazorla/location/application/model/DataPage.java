package com.corpcazorla.location.application.model;

import java.util.List;

/**
 * Contenedor genérico para resultados paginados en la capa de dominio.
 * @param <T> El tipo de objeto de dominio (ej. Paciente, Examen).
 */
public class DataPage<T> {
	
	private final List<T> data;
    private final Integer totalElements;

    public DataPage(List<T> data, Integer totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }

    public List<T> getData() {
        return data;
    }

    public Integer getTotalElements() {
        return totalElements;
    }
}
