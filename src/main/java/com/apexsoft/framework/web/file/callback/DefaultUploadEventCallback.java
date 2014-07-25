package com.apexsoft.framework.web.file.callback;

import com.apexsoft.framework.persistence.file.PersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.web.file.exception.UploadException;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public abstract class DefaultUploadEventCallback<T, P> extends UploadEventCallbackHandler<T, P> {

	public static final int DEFAULT_UPLOAD_FILESIZE = 10 * 1024 * 1024;
	
	/**
	 * <pre>
	 * 업로드 되는 파일의 크기 제한 설정 (기본값을 10M로 정의한다.)
	 * </pre>
	 */
	protected long getMaxUploadSize() {
		return DEFAULT_UPLOAD_FILESIZE;
	}

	/*
	 * (non-Javadoc)
	 * @see com.skp.commons.web.file.callback.UploadEventCallbackHandler#getDirectory(java.lang.String, java.lang.Object)
	 */
	protected abstract String getDirectory(String fileFieldName, P attribute, String leafDirectory);

	/*
	 * (non-Javadoc)
	 * @see com.skp.commons.web.file.callback.UploadEventCallbackHandler#createFileName(java.lang.String, java.lang.String, int)
	 */
	protected abstract String createFileName(String fileFieldName, String originalFileName, P attribute);

	/*
	 * (non-Javadoc)
	 * @see com.skp.commons.web.file.callback.UploadEventCallbackHandler#handleEvent(java.util.List, java.lang.Object, com.skp.commons.web.file.persistence.PersistenceManager)
	 */
	public T handleEvent(List<FileItem> fileItems, P attribute, PersistenceManager persistence)	{
		List<FileInfo> serverFileInfos = new ArrayList<FileInfo>();
		
		for (FileItem fileItem : fileItems) {
			
			if(getMaxUploadSize() > 0 && fileItem.getFile().length() > getMaxUploadSize())	{
				throw new UploadException(String.format("업로드된 파일의 크기가 지정된 최대치를 초과하였습니다. - fileSize=%d, MaxFileSize=%d", fileItem.getFile().length(), getMaxUploadSize()));
			}

			String directory = getDirectory(fileItem.getFieldName(), attribute, null);
			String serverFileName = createFileName(fileItem.getFieldName(), fileItem.getOriginalFileName(), attribute);
			
			if (!StringUtils.hasText(serverFileName)) {
				throw new UploadException("파일명이 정의되지 않았습니다.");
			}
			
			InputStream fis = null;
			
			try {
				fis = new FileInputStream(fileItem.getFile());
				serverFileInfos.add(persistence.save(directory, serverFileName, "", fis));
			} catch (IOException e) {
				throw new UploadException("", e);
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
				}
			}
		}
		
		return (T) serverFileInfos;
	}

	/*
	 * (non-Javadoc)
	 * @see com.skp.commons.web.file.callback.UploadEventCallbackHandler#postProcessor(java.util.List, java.lang.Object)
	 */
	protected T postProcessor(List<FileInfo> fileInfos, P attribute) {
		return null;
	}

}
