package com.apexsoft.framework.persistence.file.callback;

import com.apexsoft.framework.persistence.file.manager.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.web.file.exception.UploadException;
import org.apache.commons.io.FileUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 업로드 이벤트에 대한 처리 인터페이스.
 * <p>
 * 예를 들어 게시판의 첨부파일 업로드 처리를 할 때의 첨부파일 묶음 크기 제한.
 *
 * <p>
 * 1. MultiPart 스트림을 처리한 후
 * <p>
 * 2. 첨부파일 이외의 내용들에 대하여 데이터베이스에 저장하는등의 동작을 처리
 *
 */
public abstract class FileUploadEventCallbackHandler<T, P> {
	
	protected static final int UNLIMITED = Integer.MIN_VALUE;
    protected static final int MAX_LENGTH = 3*1024*1024;
	
	/**
     * 최대 업로드 사이즈 지정. (주 : 파일 단위가 아닌 멀티파트 요청 단위임)
     * 0보다 작을 경우 크기 제한을 하지 않는다.
     * <p>
     *
     * @return 멀티파트 업로드 사이즈 in bytes.
     */
    protected long getMaxUploadSize() {
    	return MAX_LENGTH;
    }
    
	/**
	 * 
	 * @param persistence
	 * @param fileItem
	 * @param directory
	 * @param fileName
	 */
	protected FileInfo savePhysicalFile(FilePersistenceManager persistence, FileItem fileItem, String directory, String fileName) {
		FileInputStream fis = null;
		
		try	{
			return persistence.save(directory, fileName, fileItem.getOriginalFileName(), (fis = new FileInputStream(fileItem.getFile())));
		} catch(Exception e){
			throw new UploadException("", e);
		} finally	{
			if(fis != null)	{
				try {
					fis.close();
					FileUtils.deleteQuietly(fileItem.getFile());
				} catch (IOException e) {
				}
			}
		}
	}

	/**
     * 어느 폴더로 업로드할 것인지 지정.
     * omw - target 폴더 이름 반환
     * <p>
     * @param fileFieldName
     * @param attributes
	 * @param leafDirectory 
     * 
     * @return
     */
    protected abstract String getDirectory(String fileFieldName, P attributes, String leafDirectory);
	
	/**
     * 멀티파트 업로드 요청 안의 개별 파일에 대한 Persistence 이름을 결정.
     * omw - 새로 저장될 이름 반환.
     * <p>
     *
     * @param fileFieldName
	 * @param originalFileName
	 * @param attribute 
	 * @return
     */
    protected abstract String createFileName(String fileFieldName, String originalFileName, P attribute);

    /**
     * 
     * @param fileItems
     * @param attribute
     * @param persistence
     * @return
     */
	public abstract T handleEvent(List<FileItem> fileItems, P attribute, FilePersistenceManager persistence);
    
}
