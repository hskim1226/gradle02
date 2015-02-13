package com.apexsoft.framework.persistence.file.receiver;

import com.apexsoft.framework.persistence.file.model.MultiPartInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 멀티파트 파일 업로드 요청 처리.
 * <p>
 *
 */
public interface MultiPartReceiver {

    /**
     * 멀티파트 파일 업로드 요청에 대하여 단순 필드는 attributes로, 전송된 파일은  로컬 파일 시스템에 임시 저장 함
     *
     * @param request
     * @return 멀티파트 폼 필드 및 파일 레퍼런스 정보.
     */
	MultiPartInfo receive(HttpServletRequest request);

}