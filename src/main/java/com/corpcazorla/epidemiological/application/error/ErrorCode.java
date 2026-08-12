package com.corpcazorla.epidemiological.application.error;

public enum ErrorCode {

    INVALID_AMOUNT("CLAIM_AMOUNT_INVALID", 400),
    VALIDATION_ERROR("VALIDATION_ERROR", 400),
    CUSTOMER_NOT_FOUND("CUSTOMER_NOT_FOUND", 404),
    DATABASE_ERROR("DATABASE_ERROR", 500),
    INTERNAL_ERROR("INTERNAL_SERVER_ERROR", 500);

    private final String code;
    private final int httpStatus;

    ErrorCode(String code, int httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
