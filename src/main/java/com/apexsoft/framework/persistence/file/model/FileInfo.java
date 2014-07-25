package com.apexsoft.framework.persistence.file.model;

/**
 * 서버 스토리지에 저장된 개별 파일 정보.
 *
 */
public final class FileInfo {

	private String directory;
	private String fileName;
	private long fileSize;
	private String orgFileName;
	
	/**
	 * 
	 * @param directory
	 * @param fileName
	 * @param orgFileName
	 * @param fileSize
	 */
	public FileInfo(String directory, String fileName, String orgFileName, long fileSize) {
		this.directory = directory;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.orgFileName = orgFileName;
	}

	/**
	 * 
	 * @param directory
	 * @param fileName
	 * @param fileSize
	 */
	public FileInfo(String directory, String fileName, long fileSize) {
		this(directory, fileName, "", fileSize);
	}

	/**
	 * 
	 * @return
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 
	 * @return
	 */
	public long getFileSize() {
		return fileSize;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOrgFileName() {
		return orgFileName;
	}

}
