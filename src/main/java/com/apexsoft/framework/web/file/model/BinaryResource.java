package com.apexsoft.framework.web.file.model;

import org.apache.commons.io.FilenameUtils;

/**
 * 
 */
public final class BinaryResource {

	private String directory;
	private String fileName;
	private String clientFileName;

	/**
	 * 
	 * @param directory
	 * @param fileName
	 * @param clientFileName
	 */
	public BinaryResource(String directory, String fileName, String clientFileName) {
		this.directory = directory;
		this.fileName = fileName;
		this.clientFileName = clientFileName;
	}

	/**
	 * 
	 * @param directory
	 * @param fileName
	 */
	public BinaryResource(String directory, String fileName) {
		this(directory, fileName, null);
	}

	/**
	 * 
	 * @param filePathName
	 */
	public BinaryResource(String filePathName) {
		this(FilenameUtils.getFullPathNoEndSeparator(filePathName), FilenameUtils.getName(filePathName), null);
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
	public String getClientFileName() {
		return clientFileName;
	}
}
