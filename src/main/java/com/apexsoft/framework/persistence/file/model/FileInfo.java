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
    private int pageCnt;
	
	/**
	 * 
	 * @param directory
	 * @param fileName
	 * @param orgFileName
	 * @param fileSize
	 */
	public FileInfo(String directory, String fileName, String orgFileName, long fileSize, int pageCnt) {
		this.directory = directory;
		this.fileName = fileName;
		this.fileSize = fileSize;
        this.pageCnt = pageCnt;
		this.orgFileName = orgFileName;
	}

    /**
     *
     * @param directory
     * @param fileName
     * @param fileSize
     */
    public FileInfo(String directory, String fileName, String originalFileName, long fileSize) {
        this(directory, fileName, originalFileName, fileSize, 0);
    }

	/**
	 * 
	 * @param directory
	 * @param fileName
	 * @param fileSize
	 */
	public FileInfo(String directory, String fileName, long fileSize) {
		this(directory, fileName, "", fileSize, 0);
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

    public int getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(int pageCnt) {
        this.pageCnt = pageCnt;
    }
}
