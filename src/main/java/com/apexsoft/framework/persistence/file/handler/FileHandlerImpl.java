package com.apexsoft.framework.persistence.file.handler;

import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.callback.FileUploadEventCallbackHandler;
import com.apexsoft.framework.persistence.file.exception.FileUploadException;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.persistence.file.model.MultiPartInfo;
import com.apexsoft.framework.persistence.file.receiver.MultiPartReceiver;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * {@link FileHandler} 의 구현체
 *
 */
public class FileHandlerImpl implements FileHandler {

	private String defaultEncoding = System.getProperty("file.encoding");

	private HttpServletRequest request;
	private HttpServletResponse response;
	private MultiPartReceiver receiver;
	private FilePersistenceManager persistence;
	private Validator validator;
	
	/**
	 * 
	 * @param request
	 * @param response TODO
	 * @param receiver
	 * @param persistence
	 */
	public FileHandlerImpl(HttpServletRequest request, HttpServletResponse response, MultiPartReceiver receiver, FilePersistenceManager persistence) {
		this(request, response, receiver, persistence, null);
	}

	/**
	 * 
	 * @param request
	 * @param response TODO
	 * @param receiver
	 * @param persistence
	 * @param validator
	 */
	public FileHandlerImpl(HttpServletRequest request, HttpServletResponse response, MultiPartReceiver receiver, FilePersistenceManager persistence, Validator validator) {
		this.request = request;
		this.response = response;
		this.receiver = receiver;
		this.persistence = persistence;
		this.validator = validator;
	}

	/*
	 * (non-Javadoc)
	 * @see com.skp.commons.file.FileHandler#isMultiPartRequest(javax.servlet.http.HttpServletRequest)
	 */
	public boolean isMultiPartRequest(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(request);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.skp.icms.commons.file.FileHandler#handMultiPartRequest(com.skp.icms.commons.file.callback.UploadEventCallbackHandler)
	 */
	public <T, P> T handleMultiPartRequest(FileUploadEventCallbackHandler<T, P> callback, Class<P> type) {

		if (callback == null) {
			throw new FileUploadException("UploadEventCallback가 정의되지 않았습니다.");
		}
		
		MultiPartInfo info = extractMultiPartInfo(request);
		
		List<FileItem> fileItems = info.getFileItem();
		Map<String, Object> attributes = info.getAttributes();
		
		P parameter = convertAttribute(attributes, type);
		
		T result = callback.handleEvent(fileItems, parameter, persistence);

        String contentType = MediaType.APPLICATION_JSON_VALUE;
//		String contentType = MediaType.TEXT_HTML_VALUE;
//		if (StringUtils.hasText(request.getHeader("Accept"))) {
//			contentType = request.getHeader("Accept");
//		}
		
		response.setContentType(contentType);
		
		return result;
	}

	/**
	 * 
	 * @param attributes
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <P> P convertAttribute(final Map<String, Object> attributes, Class<P> type) {

		if(Void.class.isAssignableFrom(type))	{
			return null;
		}
		
		if(Map.class.isAssignableFrom(type)){
			return (P) attributes;
		} else {
			final P object = newInstance(type);
			
			ReflectionUtils.doWithFields(type, new ReflectionUtils.FieldCallback() {
				
				@Override
				public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
					ReflectionUtils.makeAccessible(field);
					ReflectionUtils.setField(field, object, attributes.get(field.getName()));
				}
			});
			
			if (validator != null) {
				Set<ConstraintViolation<P>> constraintViolations = validator.validate(object);

				if(!constraintViolations.isEmpty())	{
					Iterator<ConstraintViolation<P>> it = constraintViolations.iterator();

					StringBuilder builder = new StringBuilder();

					while (it.hasNext()) {
						ConstraintViolation<P> c = it.next();

						builder.append(c.getMessage()).append("\n");
					}
					
					throw new RuntimeException(builder.toString());
				}
			}
				
			return object;
		}
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	protected <P> P newInstance(Class<P> type) {
		P object = null;
				
		try {
			object = type.newInstance();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

		return object;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	protected MultiPartInfo extractMultiPartInfo(HttpServletRequest request) {
		boolean isMultipart = isMultiPartRequest(request);
		
		if (!isMultipart) {
			throw new FileUploadException("not a multipart request.");
		}
		
		if (request.getCharacterEncoding() == null) {
			try {
				request.setCharacterEncoding(defaultEncoding);
			} catch (UnsupportedEncodingException e) {
				throw new FileUploadException("error setting character encoding on request.", e);
			}
		}

		return receiver.receive(request);
	}

}
