package com.apexsoft.framework.persistence.file.model;

import java.io.File;

/**
 * 업로드 개별 파일 정보.
 *
 */
public final class FileItem {

	private String fieldName;
	private String originalFileName;
	private File file;

	/**
	 * 
	 * @param fieldName
	 * @param originalFileName
	 * @param file
	 */
	public FileItem(String fieldName, String originalFileName, File file) {
		this.fieldName = fieldName;
		this.originalFileName = originalFileName;
		this.file = file;
	}

	/**
	 * 
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * 
	 * 멀티파트 업로드 폼에서 주어진 파일 이름.
	 * 
	 * @return
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}

	/**
	 * 
	 * @return
	 */
	public File getFile() {
		return file;
	}
}
