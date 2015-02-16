package com.apexsoft.framework.exception;

/**
 * Error Message Builder
 */
public class ErrorMessageBuilder {
	private String code;
	private String[] arguments;
	private String defaultMessage;

	/**
	 * 생성.
	 * 
	 * @return {@link ErrorMessageBuilder}
	 */
	public static ErrorMessageBuilder create() {
		return new ErrorMessageBuilder();
	}

	/**
	 * 코드.
	 *
	 * @param code
	 *            코드
	 * @return {@link ErrorMessageBuilder}
	 */
	public ErrorMessageBuilder code(String code) {
		this.code = code;

		return this;
	}

	/**
	 * 아규먼트.
	 *
	 * @param arguments
	 *            아규먼트
	 * @return {@link ErrorMessageBuilder}
	 */
	public ErrorMessageBuilder arguments(String... arguments) {
		this.arguments = arguments;

		return this;
	}

	/**
	 * 기본 메시지.
	 *
	 * @param defaultMessage
	 *            기본 메시지
	 * @return {@link ErrorMessageBuilder}
	 */
	public ErrorMessageBuilder defaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;

		return this;
	}

	/**
	 * Build.
	 * 
	 * @return {@link ErrorMessage}
	 */
	public ErrorMessage build() {
		return new ErrorMessage(code, arguments, defaultMessage);
	}
}
