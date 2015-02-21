package com.apexsoft.framework.persistence.file.handler;

import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.callback.FileUploadEventCallbackHandler;
import com.apexsoft.framework.persistence.file.exception.FileUploadException;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.persistence.file.model.MultiPartInfo;
import com.apexsoft.framework.persistence.file.receiver.MultiPartReceiver;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;

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

	/**
	 * 파일 저장 시 DB에 저장할 데이터를 DomainObj에 담아둔다.
	 *
	 * 2015.01.30 omw
	 *
	 * @param callback
	 * @param AjaxReturnObj
	 * @param domainObject
	 * @param <T>
	 * @param <P>
	 * @param <Q>
	 * @return
	 */
	public <T, P, Q> T handleMultiPartRequest(FileUploadEventCallbackHandler<T, P, Q> callback,
											  Class<P> AjaxReturnObj,
											  Class<Q> domainObject) {

		if (callback == null) {
			throw new FileUploadException("ERR0055");
		}

		MultiPartInfo info = extractMultiPartInfo(request);

		List<FileItem> fileItems = info.getFileItem();
		Map<String, Object> attributes = info.getAttributes();

		P fileInfo = convertAttribute(attributes, AjaxReturnObj);

		Q dbInfo = convertAttribute(attributes, domainObject);

		T result = callback.handleEvent(fileItems, fileInfo, persistence, dbInfo);

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

                    // omw added
					Object value = attributes.get(field.getName());
					Class fieldType = field.getType();

                    if (value == null) {
                        if (fieldType.equals(boolean.class))
                            value = false;
                        else if (fieldType.equals(Integer.class) || fieldType.equals(int.class))
                            value = -1;
                    } else if (value instanceof String) {
                        String stringValue = (String)value;
                        if (fieldType.equals(boolean.class))
                            value = BooleanUtils.toBoolean(stringValue);
                        else if (fieldType.equals(Integer.class) || fieldType.equals(int.class))
                            value = NumberUtils.toInt(stringValue, -1);
                    }
                    // omw added

					ReflectionUtils.setField(field, object, value);
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
			throw new FileUploadException("ERR0053");
		}
		
		if (request.getCharacterEncoding() == null) {
			try {
				request.setCharacterEncoding(defaultEncoding);
			} catch (UnsupportedEncodingException e) {
				throw new FileUploadException("ERR0054", e);
			}
		}

		return receiver.receive(request);
	}

}
