package com.apexsoft.framework.persistence.file.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.core.NestedRuntimeException;

/**
 * 파일 업로드 모듈에서의 오류.
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class FileUploadException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	private String userMessageCode;

	private String errorCode;

	private ExecutionContext ec;

	/**
	 *
	 * @param msg
	 */
	public FileUploadException(String msg) {
		super(msg);
	}

	/**
	 *
	 * @param msg
	 * @param ex
	 */
	public FileUploadException(String msg, Exception ex) {
		super(msg, ex);
	}

	public FileUploadException(ExecutionContext ec) {
		super(ec.getMessage());
		this.ec = ec;
	}

	public FileUploadException(ExecutionContext ec, String userMessage, String errorCode) {
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
