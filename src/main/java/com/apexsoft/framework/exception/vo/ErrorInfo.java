package com.apexsoft.framework.exception.vo;

import com.apexsoft.framework.common.vo.CommonInfo;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 예외정보
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement
public class ErrorInfo extends CommonInfo {

	private static final long serialVersionUID = 1L;

	private String code;

	@JsonIgnore
	private Object[] args;

	private String message;

	private String hostName;

	private String instanceName;

	private ErrorInfo cause;

	/**
	 * <pre>
	 * 에러코드 취득메소드.
	 * </pre>
	 * 
	 * @return 에러코드
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * <pre>
	 * 에러코드 설정메소드.
	 * </pre>
	 * 
	 * @param code
	 *            에러코드
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * <pre>
	 * 에러메시지 취득메소드.
	 * </pre>
	 * 
	 * @return 에러메시지
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * <pre>
	 * 에러메시지 설정메소드.
	 * </pre>
	 * 
	 * @param message
	 *            에러메시지
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * <pre>
	 * 원인에러 취득메소드.
	 * </pre>
	 * 
	 * @return 원인에러
	 */
	public ErrorInfo getCause() {
		return this.cause;
	}

	/**
	 * <pre>
	 * 원인에러 설정메소드.
	 * </pre>
	 * 
	 * @param cause
	 *            원인에러
	 */
	public void setCause(ErrorInfo cause) {
		this.cause = cause;
	}

	/**
	 * <pre>
	 * 에러메시지아큐먼트 취득메소드.
	 * </pre>
	 * 
	 * @return 에러메시지아큐먼트
	 */
	public Object[] getArgs() {
		return this.args;
	}

	/**
	 * <pre>
	 * 에러메시지아큐먼트 설정메소드.
	 * </pre>
	 * 
	 * @param args
	 *            에러메시지아큐먼트
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}

	/**
	 * <pre>
	 * 호스트명 설정메소드.
	 * </pre>
	 * 
	 * @return 호스트명
	 */
	public String getHostName() {
		return this.hostName;
	}

	/**
	 * <pre>
	 * 호스트명 취득메소드.
	 * </pre>
	 * 
	 * @param hostName
	 *            호스트명
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * <pre>
	 * 인스턴스명 취득메소드.
	 * </pre>
	 * 
	 * @return 인스턴스명
	 */
	public String getInstanceName() {
		return this.instanceName;
	}

	/**
	 * <pre>
	 * 인스턴스명 설정메소드.
	 * </pre>
	 * 
	 * @param instanceName
	 *            인스턴스명
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

}
