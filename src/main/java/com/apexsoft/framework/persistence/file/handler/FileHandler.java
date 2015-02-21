package com.apexsoft.framework.persistence.file.handler;

import com.apexsoft.framework.persistence.file.callback.FileUploadEventCallbackHandler;


/**
 * 파일처리 작업을 수행하며 업무 로직은 UploadEventCallback 를 받아서 처리하도록 하여 업로드와 업무로직 서비스 인스턴스 분리함
 *
 * 
 */
public interface FileHandler {

	/**
	 *
	 *
	 * @param callback
	 * @param type               Ajax로 반환해 줄 파일 메타 정보를 담는 객체
	 * @param domainObject       DB에 저장할 도메인 업무 정보를 담는 객체
	 * @param <T>                type에 담겨있는 파일 메타 정보를 갖고 있는 데이터 타입(보통 json string)
	 * @param <P>                Ajax로 반환해 줄 파일 메타 정보를 담는 데이터 타입
	 * @param <Q>                Ajax로 받은 도메인 업무 정보를 담을 데이터 타입
	 * @return
	 */
	<T, P, Q> T handleMultiPartRequest(FileUploadEventCallbackHandler<T, P, Q> callback, Class<P> type, Class<Q> domainObject);

}
