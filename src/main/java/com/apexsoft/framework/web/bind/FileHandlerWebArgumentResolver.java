package com.apexsoft.framework.web.bind;

import com.apexsoft.framework.persistence.file.PersistenceManager;
import com.apexsoft.framework.web.file.FileHandler;
import com.apexsoft.framework.web.file.FileHandlerImpl;
import com.apexsoft.framework.web.file.receiver.MultiPartReceiver;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

/**
 * 
 */
public class FileHandlerWebArgumentResolver implements WebArgumentResolver {

	private MultiPartReceiver multiPartReceiver;
	private PersistenceManager persistenceManager;
	private Validator validator;
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.bind.support.WebArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.context.request.NativeWebRequest)
	 */
	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		
		if(FileHandler.class.isAssignableFrom(methodParameter.getParameterType()))	{
			return new FileHandlerImpl((HttpServletRequest) webRequest.getNativeRequest(), (HttpServletResponse) webRequest.getNativeResponse(), multiPartReceiver, persistenceManager, validator);
		}
		
		return UNRESOLVED;
	}

	/**
	 * 
	 * @param multiPartReceiver
	 */
	public void setMultiPartReceiver(MultiPartReceiver multiPartReceiver) {
		this.multiPartReceiver = multiPartReceiver;
	}
	
	/**
	 * 
	 * @param persistenceManager
	 */
	public void setPersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}
	
	/**
	 * 
	 * @param validator
	 */
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
}
