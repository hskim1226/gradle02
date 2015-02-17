package com.apexsoft.framework.web.file;

import com.apexsoft.framework.web.file.callback.UploadEventCallbackHandler;


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
	<T, P> T handleMultiPartRequest(UploadEventCallbackHandler<T, P> callback, Class<P> type);

}
