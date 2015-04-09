package com.apexsoft.framework.persistence.file.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.core.NestedRuntimeException;

/**
 * 파일 핸들링 관련 사용자 알림 또는 디버그 성 예외
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class FileNoticeException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	private String userMessageCode;

	private String errorCode;

	private ExecutionContext ec;

	/**
	 *
	 * @param msg
	 */
	public FileNoticeException(String msg) {
		super(msg);
	}

	/**
	 *
	 * @param msg
	 * @param ex
	 */
	public FileNoticeException(String msg, Exception ex) {
		super(msg, ex);
	}

	public FileNoticeException(ExecutionContext ec) {
		super(ec.getMessage());
		this.ec = ec;
	}

	public FileNoticeException(ExecutionContext ec, String userMessage, String errorCode) {
		super(ec.getMessage());
		this.ec = ec;
		this.userMessageCode = userMessage;
		this.errorCode = errorCode;
	}

	public ExecutionContext getExecutionContext() {
		return ec;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getUserMessageCode() {
		return userMessageCode;
	}

}
