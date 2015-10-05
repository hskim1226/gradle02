package com.apexsoft.framework.persistence.file.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.core.NestedRuntimeException;

/**
 * PDF 파일이 합침이 되지 않을 때 발생
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class PDFMergeException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	private String userMessageCode;

	private String errorCode;

	private ExecutionContext ec;

	/**
	 *
	 * @param msg
	 */
	public PDFMergeException(String msg) {
		super(msg);
	}

	/**
	 *
	 * @param msg
	 * @param ex
	 */
	public PDFMergeException(String msg, Exception ex) {
		super(msg, ex);
	}

	public PDFMergeException(ExecutionContext ec) {
		super(ec.getMessage());
		this.ec = ec;
	}

	public PDFMergeException(ExecutionContext ec, String userMessageCode, String errorCode) {
		super(ec.getMessage());
		this.ec = ec;
		this.userMessageCode = userMessageCode;
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
