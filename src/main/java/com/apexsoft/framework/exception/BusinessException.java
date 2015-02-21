package com.apexsoft.framework.exception;

/**
 * Store Platform Exception
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    private String errorError;

	/**
	 * @param errorError
	 *            에러정보
	 */
	public BusinessException(String errorError) {
		super(errorError);
		this.errorError = errorError;

	}

	/**
	 * @param errorError
	 *            에러정보
	 * @param cause
	 *            원인 에러
	 */
	public BusinessException(String errorError, Throwable cause) {
		super(errorError, cause);
		this.errorError = errorError;
	}

    public String getErrorError() {
        return errorError;
    }

    public void setErrorError(String errorError) {
        this.errorError = errorError;
    }
}
