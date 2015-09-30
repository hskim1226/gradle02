package com.apexsoft.framework.persistence.file.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.core.NestedRuntimeException;

/**
 * 업로드 중인 PDF 파일이 암호화 되어 있을 때 발생
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class EncryptedPDFException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	private String userMessageCode;

	private String errorCode;

	private ExecutionContext ec;

	/**
	 *
	 * @param msg
	 */
	public EncryptedPDFException(String msg) {
		super(msg);
	}

	/**
	 *
	 * @param msg
	 * @param ex
	 */
	public EncryptedPDFException(String msg, Exception ex) {
		super(msg, ex);
	}

	public EncryptedPDFException(ExecutionContext ec) {
		super(ec.getMessage());
		this.ec = ec;
	}

	public EncryptedPDFException(ExecutionContext ec, String userMessage, String errorCode) {
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
