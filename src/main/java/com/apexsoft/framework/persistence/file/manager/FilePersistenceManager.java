package com.apexsoft.framework.persistence.file.manager;

import com.apexsoft.framework.persistence.file.model.FileInfo;

import java.io.File;
import java.io.InputStream;


/**
 * 파일 저장소 관리.
 * <p>
 * 
 */
public interface FilePersistenceManager {

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	File read(String fileName);
	
	/**
	 * 
	 * @param folder
	 * @param fileName
	 * @return
	 */
	File read(String folder, String fileName);
	
	/**
	 * 
	 * 폴더 생성
	 * 
	 * @param folder
	 * @return
	 */
	File makeDirectory(String folder);
	
	/**
	 * 폴더 삭제.
	 * 
	 * @param folder
	 */
	void delete(String folder);

	/**
	 * 폴더 안의 개별 파일 삭제.
	 * 
	 * @param folder
	 * @param fileName
	 */
	void deleteFile(String folder, String fileName);

	/**
	 * 파일 저장 및 파일 정보 반환.
	 *
	 * @param folder
	 * @param fileName
	 * @param orgFileName TODO
	 * @param stream
	 * @return fileLength, 기타 생성 데이터 모델 포함.
	 */
	FileInfo save(String folder, String fileName, String orgFileName, InputStream stream);
	
	/**
	 * 
	 * @param source
	 * @param folder
	 * @param fileName
	 * @return
	 */
	FileInfo moveFile(File source, String folder, String fileName);

	/**
	 * 
	 * @param srcFolder
	 * @param srcFileName
	 * @param tarFolder
	 * @param tarFileName
	 * @return
	 */
	FileInfo copyFile(String srcFolder, String srcFileName, String tarFolder, String tarFileName);
}
