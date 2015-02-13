package com.apexsoft.framework.exception;

/**
 * ErrorMessage
 */
public class ErrorMessage {
	private final String code;
	private final Object[] arguments;
	private final String defaultMessage;

	/**
	 * 생성자.
	 * 
	 * @param code
	 *            코드
	 * @param arguments
	 *            아규먼트
	 * @param defaultMessage
	 *            기본 메시지
	 */
	ErrorMessage(String code, Object[] arguments, String defaultMessage) {
		super();

		this.code = code;
		this.arguments = arguments;
		this.defaultMessage = defaultMessage;
	}

	/**
	 * 코드.
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 아규먼트.
	 * 
	 * @return arguments
	 */
	public Object[] getArguments() {
		return arguments;
	}

	/**
	 * 기본 메시지.
	 * 
	 * @return defaultMessage
	 */
	public String getDefaultMessage() {
		return defaultMessage;
	}

}
