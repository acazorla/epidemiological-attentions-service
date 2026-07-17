package com.corpcazorla.location.application.exception;

import com.corpcazorla.location.application.error.ErrorCode;

public class BusinessException extends RuntimeException{	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private final ErrorCode errorCode;

    // Constructor principal
    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    // Constructor con causa (muy importante para debugging)
    public BusinessException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getCode() {
        return errorCode.getCode();
    }

    public int getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}

