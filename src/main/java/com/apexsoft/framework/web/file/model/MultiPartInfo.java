package com.apexsoft.framework.web.file.model;

import com.apexsoft.framework.persistence.file.model.FileItem;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * HTTP 파일 업로드에서의 Multipart Form 해석 정보 저장.
 *
 */
public final class MultiPartInfo {

	private Map<String, Object> attributes;
	private List<FileItem> fileItem;
	
	/**
	 * 
	 * @param attributes
	 * @param fileItem
	 */
	public MultiPartInfo(Map<String, Object> attributes, List<FileItem> fileItem) {
		this.attributes = Collections.unmodifiableMap(attributes);
		this.fileItem = Collections.unmodifiableList(fileItem);
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<FileItem> getFileItem() {
		return fileItem;
	}
	
}
