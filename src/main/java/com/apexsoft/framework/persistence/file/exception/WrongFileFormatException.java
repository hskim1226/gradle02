package com.apexsoft.framework.persistence.file.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.core.NestedRuntimeException;

/**
 * PDF 파일이 합침이 되지 않을 때 발생
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class WrongFileFormatException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	private String userMessageCode;

	private String errorCode;

	private String errorFileName ;

	private ExecutionContext ec;



	/**
	 *
	 * @param msg
	 */
	public WrongFileFormatException(String msg) {
		super(msg);
	}

	/**
	 *
	 * @param msg
	 * @param ex
	 */
	public WrongFileFormatException(String msg, Exception ex) {
		super(msg, ex);
	}

	public WrongFileFormatException(ExecutionContext ec) {
		super(ec.getMessage());
		this.ec = ec;
	}

	public WrongFileFormatException(ExecutionContext ec, String userMessageCode, String errorCode) {
		super(ec.getMessage());
		this.ec = ec;
		this.userMessageCode = userMessageCode;
		this.errorCode = errorCode;
	}

	public WrongFileFormatException(ExecutionContext ec, String userMessageCode, String errorCode, String errorFileName) {
		super(ec.getMessage());
		this.ec = ec;
		this.userMessageCode = userMessageCode;
		this.errorCode = errorCode;
		this.errorFileName = errorFileName;
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
