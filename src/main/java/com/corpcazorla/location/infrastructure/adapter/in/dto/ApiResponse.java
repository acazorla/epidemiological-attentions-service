package com.corpcazorla.location.infrastructure.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private ErrorDetail error;
    private Meta meta;

 // 1. Constructor vacío: Esencial para Jackson y para el GlobalExceptionMapper
    public ApiResponse() {
        this.meta = new Meta(); // Evita que meta sea null en el JSON
    }
 // 2. Constructor para Éxito: El que usa tu PacienteController
    public ApiResponse(T data, Meta meta) {
        this(); // Llama al constructor vacío para inicializar el meta
        this.success = true;
        this.data = data;
        this.meta = meta;
    }

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public ErrorDetail getError() {
		return error;
	}
	public void setError(ErrorDetail error) {
		this.error = error;
	}
}
