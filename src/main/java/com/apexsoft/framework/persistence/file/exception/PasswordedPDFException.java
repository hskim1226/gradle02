package com.apexsoft.framework.persistence.file.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.core.NestedRuntimeException;

/**
 * 업로드 중인 PDF 파일이 암호화 되어 있을 때 발생
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class PasswordedPDFException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	private String userMessageCode;

	private String errorCode;

	private ExecutionContext ec;

	/**
	 *
	 * @param msg
	 */
	public PasswordedPDFException(String msg) {
		super(msg);
	}

	/**
	 *
	 * @param msg
	 * @param ex
	 */
	public PasswordedPDFException(String msg, Exception ex) {
		super(msg, ex);
	}

	public PasswordedPDFException(ExecutionContext ec) {
		super(ec.getMessage());
		this.ec = ec;
	}

	public PasswordedPDFException(ExecutionContext ec, String userMessage, String errorCode) {
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
