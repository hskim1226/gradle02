package com.apexsoft.ysprj.common.exception;

import com.apexsoft.ysprj.common.exception.vo.ErrorInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * Store Platform Exception
 */
public class StorePlatformException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String defaultMessage;

	private ErrorInfo errorInfo;

	/**
	 * <pre>
	 * 디폴트메시지.
	 * </pre>
	 * 
	 * @param defaultMessage
	 *            디폴트메시지
	 * @return 자기자신
	 */
	public StorePlatformException setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
		return this;
	}

	/**
	 * <pre>
	 * 코드를 가져오는 메소드.
	 * </pre>
	 * 
	 * @return 코드
	 */
	public String getCode() {
		if (this.errorInfo == null || StringUtils.isEmpty(this.errorInfo.getCode())) {
			return null;
		}

		return this.errorInfo.getCode();
	}

	/**
	 * @param errorInfo
	 *            에러정보
	 */
	public StorePlatformException(ErrorInfo errorInfo) {
		super(errorInfo.getCode());
		this.errorInfo = errorInfo;

	}

	/**
	 * @param errorInfo
	 *            에러정보
	 * @param cause
	 *            원인 에러
	 */
	public StorePlatformException(ErrorInfo errorInfo, Throwable cause) {
		super(errorInfo.getCode(), cause);
		this.errorInfo = errorInfo;

	}

	/**
	 * @param code
	 *            에러코드
	 */
	public StorePlatformException(String code) {
		this(code, null, null);

	}

	/**
	 * @param code
	 *            에러코드
	 * @param args
	 *            에러메시지 아큐먼트
	 */
	public StorePlatformException(String code, Object... args) {
		this(code, null, args);

	}

	/**
	 * @param code
	 *            에러코드
	 * @param cause
	 *            원인 에러
	 */
	public StorePlatformException(String code, Throwable cause) {
		this(code, cause, null);

	}

	/**
	 * @param code
	 *            에러코드
	 * @param cause
	 *            원인 에러
	 * 
	 * @param args
	 *            에러메시지 파라미터
	 */
	public StorePlatformException(String code, Throwable cause, Object... args) {
		super(code, cause);
		this.errorInfo = new ErrorInfo();
		this.errorInfo.setCode(code);
		this.errorInfo.setArgs(args);
	}

	/**
	 * <pre>
	 * 에러정보 취득 메소드.
	 * </pre>
	 * 
	 * @return 에러정보
	 */
	public ErrorInfo getErrorInfo() {
		return this.errorInfo;
	}

	/**
	 * <pre>
	 * 에러정보 설정 메소드.
	 * </pre>
	 * 
	 * @param errorInfo
	 *            에러정보
	 */
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	@Deprecated
	protected ErrorMessage errorMessage;

	/**
	 * 생성자.
	 * 
	 * @param errorMessage
	 *            {@link ErrorMessage}
	 */
	@Deprecated
	public StorePlatformException(ErrorMessage errorMessage) {
		super();

		this.errorMessage = errorMessage;
	}

	/**
	 * 생성자.
	 * 
	 * @param errorMessage
	 *            {@link ErrorMessage}
	 * @param throwable
	 *            {@link Throwable}
	 */
	@Deprecated
	public StorePlatformException(ErrorMessage errorMessage, Throwable throwable) {
		super(errorMessage.getCode(), throwable);

		this.errorMessage = errorMessage;
	}

	/**
	 * 에러 메시지 세팅.
	 * 
	 * @param errorMessage
	 *            {@link ErrorMessage}
	 */
	@Deprecated
	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 에러 메시지 세팅.
	 * 
	 * @return {@link ErrorMessage}
	 */
	@Deprecated
	public ErrorMessage getErrorMessage() {
		return this.errorMessage;
	}

}
