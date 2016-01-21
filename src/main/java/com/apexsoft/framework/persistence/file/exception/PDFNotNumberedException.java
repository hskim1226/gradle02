package com.apexsoft.framework.persistence.file.exception;

import com.apexsoft.framework.common.vo.ExecutionContext;
import org.springframework.core.NestedRuntimeException;

/**
 * 업로드 중인 PDF 파일에 페이지 넘버링을 할 수 없을 때 발생
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class PDFNotNumberedException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	private ExecutionContext ec;

	public PDFNotNumberedException(ExecutionContext ec) {
		super(ec.getMessage());
		this.ec = ec;
	}

	public ExecutionContext getExecutionContext() {
		return ec;
	}

	public String getErrorCode() {
		return ec.getErrCode();
	}

}
