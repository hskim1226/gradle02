package com.apexsoft.framework.persistence.file.manager;

import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.web.file.exception.UploadException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FilePersistenceManagerImpl implements FilePersistenceManager {

	private static final Logger logger = LoggerFactory.getLogger(FilePersistenceManagerImpl.class);

	private File baseDirectory = new File(System.getProperty("user.home"), "upload");
	
	/**
	 * 
	 * @param baseDirectory
	 */
	public void setBaseDirectory(File baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	public File read(String fileName) {
		return new File(baseDirectory, fileName);
	}

	/*
	 * (non-Javadoc)
	 * @see com.skp.store.core.persistence.file.FilePersistenceManager#read(java.lang.String, java.lang.String)
	 */
	public File read(String folder, String fileName) {
		return new File(new File(baseDirectory, folder), fileName);
	}
	
	public void delete(String urn) {
		File uploadDirectory = new File(baseDirectory, urn);
		
		try {
			FileUtils.deleteDirectory(uploadDirectory);

		} catch (IOException e) {
			throw new UploadException("error cleaning up upload directory " + uploadDirectory, e);
		}
	}

	public void deleteFile(String urn, String individualFile) {
		File uploadDirectory = new File(baseDirectory, urn);
		File deleteFile = new File(uploadDirectory, individualFile);

		deleteFile.delete();
	}

	public FileInfo save(String folder, String fileName, String orgFileName, InputStream inputStream) {

		File uploadDirectory = new File(baseDirectory, folder);
		File uploadFile = new File(uploadDirectory, fileName);

		File tempFile;
		
		try {
			tempFile = File.createTempFile("upload-", null);
		} catch (IOException e) {
			throw new UploadException("error creating temp file", e);
		}

		FileOutputStream fos;
		
		try {
			fos = new FileOutputStream(tempFile);
		} catch (FileNotFoundException e) {
			throw new UploadException("error opening outputstream for file " + tempFile, e);
		}

		try {
			IOUtils.copyLarge(inputStream, fos);
		} catch (IOException e) {
			if (! tempFile.delete()) {
				throw new UploadException("error relaying inputStream to file, and deleting tempFile. " + tempFile, e);
			}
			throw new UploadException("error relaying inputStream to  tempFile " + tempFile, e);

		} finally {
			try {
				fos.close();

			} catch (IOException e) {
				logger.warn("error closing output stream " + tempFile, e);
			}
		}

		if (! uploadDirectory.exists()) {
			if (! uploadDirectory.mkdirs()) {
				throw new UploadException("cannot make directory ("+ uploadDirectory + ") for uploaded file content.");
			}
		}

		if (uploadFile.exists()) {
			if (! uploadFile.delete()) {
				throw new UploadException("cannot delete pre-existing file " + uploadFile);
			}
		}

		try {
			FileUtils.moveFile(tempFile, uploadFile);

		} catch (IOException e) {
			throw new UploadException("error renaming tempFile to  file " + uploadFile, e);

		}

		return new FileInfo(uploadDirectory.getAbsolutePath(), fileName, orgFileName, uploadFile.length());
	}

	/*
	 * (non-Javadoc)
	 * @see com.skp.commons.web.file.persistence.FilePersistenceManager#moveFile(java.io.File, java.lang.String, java.lang.String)
	 */
	public FileInfo moveFile(File source, String folder, String fileName)	{
		File uploadDirectory = new File(baseDirectory, folder);
		File uploadFile = new File(uploadDirectory, fileName);

		if (! uploadDirectory.exists()) {
			if (! uploadDirectory.mkdirs()) {
				throw new UploadException("cannot make directory ("+ uploadDirectory + ") for uploaded file content.");
			}
		}

		if (uploadFile.exists()) {
			if (! uploadFile.delete()) {
				throw new UploadException("cannot delete pre-existing file " + uploadFile);
			}
		}

		FileOutputStream fos;
		FileInputStream fis;
		
		try {
			fos = new FileOutputStream(uploadFile);
			fis = new FileInputStream(source);
		} catch (FileNotFoundException e) {
			throw new UploadException("error opening in/outputstream for file " + uploadFile, e);
		}

		try {
			IOUtils.copyLarge(fis, fos);
		} catch (IOException e) {
			throw new UploadException("error relaying inputStream to  tempFile " + uploadFile, e);

		} finally {
			try {
				fis.close();
				fos.close();

			} catch (IOException e) {
				logger.warn("error closing output stream " + uploadFile, e);
			}
		}

		return new FileInfo(folder, fileName, uploadFile.length());
	}

	public FileInfo copyFile(String srcFolder, String srcFileName, String tarFolder, String tarFileName)	{

		File uploadDirectory = new File(baseDirectory, tarFolder);
		File uploadFile = new File(uploadDirectory, tarFileName);

		if (! uploadDirectory.exists()) {
			if (! uploadDirectory.mkdirs()) {
				throw new UploadException("cannot make directory ("+ uploadDirectory + ") for uploaded file content.");
			}
		}

        File srcFile = read(srcFolder, srcFileName);
        if ( srcFile == null || !srcFile.exists() ) {
            logger.info("[Image Breaker Check] Soruce File not Exist {} ", srcFile.getAbsolutePath());
            return new FileInfo(tarFolder, tarFileName, uploadFile.length());
        }

        logger.debug("[Image Breaker] srcFile = {}", srcFile.getAbsolutePath());
        logger.debug("[Image Breaker] uploadFile = {}", uploadFile.getAbsolutePath());

        if ( uploadFile != null && srcFile.getAbsolutePath().equals(uploadFile.getAbsolutePath())){
            logger.info("[Image Breaker Check] Soruce File and Target File are same!!! {} ", srcFile.getAbsolutePath());
            return new FileInfo(tarFolder, tarFileName, uploadFile.length());
        }

		if (uploadFile.exists()) {
			if (! uploadFile.delete()) {
				throw new UploadException("cannot delete pre-existing file " + uploadFile);
			}
		}
		
		FileOutputStream fos;
		FileInputStream fis;
		
		try {
			fos = new FileOutputStream(uploadFile);
			fis = new FileInputStream(read(srcFolder, srcFileName));
		} catch (FileNotFoundException e) {
			throw new UploadException("error opening in/outputstream for file " + uploadFile, e);
		}
		
		try {
			IOUtils.copyLarge(fis, fos);
		} catch (IOException e) {
			throw new UploadException("error relaying inputStream to  tempFile " + uploadFile, e);
			
		} finally {
			try {
				fis.close();
				fos.close();
				
			} catch (IOException e) {
				logger.warn("error closing output stream " + uploadFile, e);
			}
		}
		
		return new FileInfo(tarFolder, tarFileName, uploadFile.length());
	}
	
	public File makeDirectory(String folder) {
		File targetDirectory = new File(baseDirectory, folder);
		
		if(!targetDirectory.exists())	{
			if (! targetDirectory.mkdirs()) {
				throw new UploadException("cannot make directory ("+ targetDirectory + ") for uploaded file content.");
			}
		}
		
		return targetDirectory;
	}

}
