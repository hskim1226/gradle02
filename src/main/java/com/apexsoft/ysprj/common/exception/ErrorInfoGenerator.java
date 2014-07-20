package com.apexsoft.ysprj.common.exception;

import com.apexsoft.ysprj.common.exception.vo.ErrorInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 * 에러정보 생성기
 */
public class ErrorInfoGenerator {

	private List<MessageSourceAccessor> messageSourceAccessorList;

	private String errorCodePrefix;

	private List<SystemErrorCodeTranslator> systemErrorCodeTranslatorList;

	public void setSystemErrorCodeTranslatorList(List<SystemErrorCodeTranslator> systemErrorCodeTranslatorList) {
		this.systemErrorCodeTranslatorList = systemErrorCodeTranslatorList;

	}

	/**
	 * <pre>
	 * 에러코드 접두어.
	 * </pre>
	 * 
	 * @param errorCodePrefix
	 *            에러코드 접두어
	 */
	public void setErrorCodePrefix(String errorCodePrefix) {
		this.errorCodePrefix = errorCodePrefix;
	}

	/**
	 * <pre>
	 * 메시지소스액세서 설정 메소드.
	 * </pre>
	 * 
	 * @param messageSourceAccessorList
	 *            메시지소스액세서 목록
	 */
	public void setMessageSourceAccessorList(List<MessageSourceAccessor> messageSourceAccessorList) {
		this.messageSourceAccessorList = messageSourceAccessorList;
	}

	private String bb(String packagee) {
		return StringUtils.defaultIfBlank(this.getSysteErrorCode(packagee), this.errorCodePrefix);
	}

	private String aa(StackTraceElement[] stackTraceElements) {
		String result = null;

		for (StackTraceElement stackTraceElement : stackTraceElements) {
			result = this.getSysteErrorCode(stackTraceElement.getClassName());

			if (result != null) {
				return result;
			}
		}

		return this.errorCodePrefix;
	}

	/**
	 * <pre>
	 * 에러정보 클래스의 계층구조 생성 및 예외메시지 바인딩 메소드.
	 * </pre>
	 * 
	 * @param ex
	 *            예외객체
	 * @param locale
	 *            로케일
	 * @return 에러정보
	 */
	public ErrorInfo createHierarchicalErrorInfoAndSetMessage(Throwable ex, Locale locale) {

		List<Throwable> throwableList = ExceptionUtils.getThrowableList(ex);

		ErrorInfo topErrorInfo = null;

		ErrorInfo beforeErrorInfo = null;

		String prefix = this.errorCodePrefix;

		for (int index = 0, max = throwableList.size(); index < max; index++) {
			Throwable throwable = throwableList.get(index);

			ErrorInfo errorInfo = null;

			if (throwable instanceof MethodArgumentNotValidException || throwable instanceof BindException
					|| throwable instanceof StorePlatformMethodArgumentNotValidException) {
				List<FieldError> fieldErrors = null;

				String errorCode = null;

				if (throwable instanceof MethodArgumentNotValidException) {
					errorCode = this.aa(throwable.getStackTrace());

					fieldErrors = ((MethodArgumentNotValidException) throwable).getBindingResult().getFieldErrors();
				} else if (throwable instanceof StorePlatformMethodArgumentNotValidException) {
					errorCode = this.bb(((StorePlatformMethodArgumentNotValidException) throwable).getParameter()
							.getMethod().getDeclaringClass().getName());

					fieldErrors = ((StorePlatformMethodArgumentNotValidException) throwable).getBindingResult()
							.getFieldErrors();

				} else {

					RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

					HandlerMethod handlerMethod = (HandlerMethod) requestAttributes.getAttribute(
							HandlerMethod.class.getName(), RequestAttributes.SCOPE_REQUEST);

					if (handlerMethod != null) {
						errorCode = this.bb(handlerMethod.getBean().getClass().getName());

					} else {
						errorCode = this.errorCodePrefix;
					}

					fieldErrors = ((BindException) throwable).getFieldErrors();

				}

				String[] feildErrors = new String[fieldErrors.size()];

				StringBuffer sb = new StringBuffer();

				int indexFieldError = 0;

				for (FieldError fieldError : fieldErrors) {
					feildErrors[indexFieldError] = this.resolveFieldErrorMessage(fieldError, locale);
					indexFieldError++;
				}

				errorInfo = new ErrorInfo();

				errorInfo.setCode(String.format("%s_%s", errorCode, "SYS_ERROR_PARAM"));

				errorInfo.setArgs(new Object[] { StringUtils.join(feildErrors, ", ") });

			} else if (throwable instanceof StorePlatformXssInvalidException) {
				String errorCode = this.aa(throwable.getStackTrace());

				errorInfo = ((StorePlatformException) throwable).getErrorInfo();

				if (StringUtils.isBlank(errorInfo.getMessage())) {
					errorInfo.setCode(errorCode + "_" + errorInfo.getCode());

				}
			} else if (throwable instanceof StorePlatformException) {
				errorInfo = ((StorePlatformException) throwable).getErrorInfo();

			} else if (throwable instanceof DataAccessException || throwable instanceof SQLException) {
				String errorCode = this.aa(throwable.getStackTrace());

				errorInfo = new ErrorInfo();
				errorInfo.setCode(String.format("%s_%s", errorCode, "SYS_ERROR_SQL"));

				String sqlErrorCode = "N/A";

				if (throwable instanceof SQLException) {
					sqlErrorCode = String.valueOf(((SQLException) throwable).getErrorCode());
				}

				errorInfo.setArgs(new Object[] { throwable.getClass().getName(), sqlErrorCode });

			} else {
				String errorCode = this.aa(throwable.getStackTrace());

				errorInfo = new ErrorInfo();

				errorInfo.setCode(String.format("%s_%s", errorCode, "SYS_ERROR"));

				String[] args = StringUtils.split(throwable.getStackTrace()[0].getClassName(), ".");

				errorInfo.setArgs(new Object[] { throwable.getClass().getName() });

			}

			if (StringUtils.isBlank(errorInfo.getMessage())) {
				errorInfo.setMessage(StringUtils.defaultIfBlank(this.resolveMessage(errorInfo, locale),
						"Not found a error message."));

			}

			try {
				errorInfo.setHostName(java.net.InetAddress.getLocalHost().getHostName());

			} catch (UnknownHostException e) {
				errorInfo.setHostName("UnknownHost");
			}

			errorInfo.setInstanceName(java.lang.System.getProperty("env.servername"));

			if (index == 0) {
				topErrorInfo = errorInfo;
			}

			if (beforeErrorInfo != null) {
				beforeErrorInfo.setCause(errorInfo);
			}

			beforeErrorInfo = errorInfo;
		}

		return topErrorInfo;
	}

	private String getSysteErrorCode(String packagee) {
		String result = null;

		if (CollectionUtils.isEmpty(this.systemErrorCodeTranslatorList)) {
			return this.errorCodePrefix;

		} else {
			for (SystemErrorCodeTranslator systemErrorCodeTranslator : this.systemErrorCodeTranslatorList) {

				result = systemErrorCodeTranslator.translate(packagee);

				if (StringUtils.isNotBlank(result)) {
					return result;
				}

			}
		}

		return result;
	}

	/**
	 * <pre>
	 * 에러코드에 해당하는 메시지를 취득하는 메소드.
	 * </pre>
	 * 
	 * @param errorInfo
	 *            에레정보
	 * @param locale
	 *            로케일
	 * @return 에러메시지
	 */
	private String resolveMessage(ErrorInfo errorInfo, Locale locale) {
		if (CollectionUtils.isNotEmpty(this.messageSourceAccessorList)) {
			for (MessageSourceAccessor accessor : this.messageSourceAccessorList) {
				try {
					String errorCode = errorInfo.getCode();

					if (StringUtils.contains(errorCode, "_SYS_ERROR")) {
						errorCode = "SYS_ERROR" + StringUtils.substringAfter(errorCode, "SYS_ERROR");
					}

					return accessor.getMessage(errorCode, errorInfo.getArgs(), locale);
				} catch (NoSuchMessageException ex) {
					// ignore
					continue;
				}
			}
		}

		return StringUtils.EMPTY;
	}

	/**
	 * <pre>
	 * 에러코드에 해당하는 메시지를 취득하는 메소드.
	 * </pre>
	 * 
	 * @param fieldError
	 *            필드에러
	 * @param locale
	 *            로케일
	 * @return 에러메시지
	 */
	private String resolveFieldErrorMessage(FieldError fieldError, Locale locale) {
		if (CollectionUtils.isNotEmpty(this.messageSourceAccessorList)) {
			for (MessageSourceAccessor accessor : this.messageSourceAccessorList) {
				try {
					String fieldErrorMessage = accessor.getMessage(fieldError);

					if (StringUtils.isBlank(fieldErrorMessage)
							|| StringUtils.equals(fieldError.getDefaultMessage(), fieldErrorMessage)) {
						continue;

					} else {
						return fieldErrorMessage;
					}

				} catch (NoSuchMessageException ex) {
					continue;
				}
			}
		}

		return fieldError.getDefaultMessage();
	}

}
