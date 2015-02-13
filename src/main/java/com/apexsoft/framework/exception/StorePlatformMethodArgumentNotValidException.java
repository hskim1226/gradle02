package com.apexsoft.framework.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * Spring F/W MethodArgumentNotValidException가 RuntimeException 버전으로 재구현한 클래스.
 * 
 * Updated on : 2013-09-01 Updated by : 최현식, 에이엔비.
 */
public class StorePlatformMethodArgumentNotValidException extends BusinessException {
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	private final MethodParameter parameter;

	private final BindingResult bindingResult;

	/**
	 * <pre>
	 *  Constructor for {@link StorePlatformMethodArgumentNotValidException}.
	 * </pre>
	 * 
	 * @param code
	 *            코드
	 * @param parameter
	 *            메소드 파라미터
	 * @param bindingResult
	 *            바인딩 리절트
	 */
	public StorePlatformMethodArgumentNotValidException(String code, MethodParameter parameter,
			BindingResult bindingResult) {
		super(code);
		this.parameter = parameter;
		this.bindingResult = bindingResult;
	}

	/**
	 * <pre>
	 * 메소드 파라미터 취득메소드.
	 * </pre>
	 * 
	 * @return 메소드 파라미터
	 */
	public MethodParameter getParameter() {
		return this.parameter;
	}

	/**
	 * <pre>
	 * 바인딩 리절트 취득메소드.
	 * </pre>
	 * 
	 * @return 바인딩 리절트
	 */
	public BindingResult getBindingResult() {
		return this.bindingResult;
	}

	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder("Validation failed for argument at index ")
				.append(this.parameter.getParameterIndex()).append(" in method: ")
				.append(this.parameter.getMethod().toGenericString()).append(", with ")
				.append(this.bindingResult.getErrorCount()).append(" error(s): ");
		for (ObjectError error : this.bindingResult.getAllErrors()) {
			sb.append("[").append(error).append("] ");
		}
		return sb.toString();
	}
}
