package com.apexsoft.framework.web.file.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 파일 업로드 모듈에서의 오류.
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class UploadException extends NestedRuntimeException {

	/**
	 * 
	 * @param msg
	 */
	public UploadException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * @param msg
	 * @param ex
	 */
	public UploadException(String msg, Exception ex) {
		super(msg, ex);
	}

}
