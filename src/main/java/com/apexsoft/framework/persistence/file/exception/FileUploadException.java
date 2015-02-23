package com.apexsoft.framework.persistence.file.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 파일 업로드 모듈에서의 오류.
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class FileUploadException extends NestedRuntimeException {

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

}
