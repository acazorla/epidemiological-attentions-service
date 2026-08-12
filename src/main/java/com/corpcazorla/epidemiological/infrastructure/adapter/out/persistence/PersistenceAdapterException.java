package com.corpcazorla.epidemiological.infrastructure.adapter.out.persistence;

public class PersistenceAdapterException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private final int code;

    // Constructor estándar
    public PersistenceAdapterException(String message, int code) {
        super(message);
        this.code = code;
    }

    // Constructor con Causa (Recomendado para persistencia)
    public PersistenceAdapterException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() { return code; }
}