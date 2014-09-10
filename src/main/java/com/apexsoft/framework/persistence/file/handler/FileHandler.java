package com.apexsoft.framework.persistence.file.handler;

import com.apexsoft.framework.persistence.file.callback.FileUploadEventCallbackHandler;


/**
 * 
 * 파일처리 작업을 수행하며 업무 로직은 UploadEventCallback 를 받아서 처리하도록 하여 업로드와 업무로직 서비스 인스턴스 분리함
 * 
 */
public interface FileHandler {

	/**
	 * 
	 * @param callback
	 * @param type
	 * @return
	 */
	<T, P> T handleMultiPartRequest(FileUploadEventCallbackHandler<T, P> callback, Class<P> type);

}
