package com.apexsoft.framework.persistence.file.receiver;

import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.persistence.file.exception.FileUploadException;
import com.apexsoft.framework.persistence.file.model.MultiPartInfo;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * 
 *  {@link MultiPartReceiver} 의 구현체
 *
 */
public class MultiPartReceiverImpl implements MultiPartReceiver {

	/*
	 * (non-Javadoc)
	 * @see com.skp.commons.file.receiver.MultiPartReceiver#receive(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public MultiPartInfo receive(HttpServletRequest request)	{
		try {
			return receiveInternal(request);
		} catch (Exception e) {
			throw new FileUploadException("ERR0056", e);
		}
	}

	private MultiPartInfo receiveInternal(HttpServletRequest request) throws Exception {

//		ServletFileUpload upload = new ServletFileUpload();
//
//		FileItemIterator iter = upload.getItemIterator(request);
//
//		Map<String, Object> attributes = new HashMap<String, Object>();
//		List<FileItem> files = new ArrayList<FileItem>();
//
//		while (iter.hasNext()) {
//			FileItemStream itemStream = iter.next();
//
//			if (itemStream.isFormField()) {
//				handleAttribute(attributes, itemStream);
//			} else {
//				File f = File.createTempFile("upload", "temp");
//				f.deleteOnExit();
//
//				OutputStream fos = new FileOutputStream(f);
//				IOUtils.copyLarge(itemStream.openStream(), fos);
//
//				files.add(new FileItem(itemStream.getFieldName(), itemStream.getName(), f));
//			}
//		}
//
//		String queryString = request.getQueryString();
//
//		if(StringUtils.hasText(queryString)){
//			attributes.putAll(getQueryParameters(queryString));
//		}
		DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = (DefaultMultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> fileMap = defaultMultipartHttpServletRequest.getMultiFileMap();
		Map<String, String[]> paramMap = defaultMultipartHttpServletRequest.getParameterMap();

//		DiskFileItemFactory factory = new DiskFileItemFactory();

//		ServletFileUpload upload = new ServletFileUpload(factory);

//		FileItemIterator iter = upload.getItemIterator(request);

//		List<org.apache.commons.fileupload.FileItem> items = upload.parseRequest(defaultMultipartHttpServletRequest);

		Map<String, Object> attributes = new HashMap<String, Object>();
		List<FileItem> files = new ArrayList<FileItem>();

		Set<Map.Entry<String, List<MultipartFile>>> entrySet = fileMap.entrySet();
		for (Map.Entry<String, List<MultipartFile>> entry : entrySet) {

			File f = File.createTempFile("upload", "temp");
			f.deleteOnExit();

			OutputStream fos = new FileOutputStream(f);
			List<MultipartFile> fileList = entry.getValue();
			for (MultipartFile file : fileList) {
				IOUtils.copyLarge(file.getInputStream(), fos);
				files.add(new FileItem(entry.getKey(), file.getName(), f));
			}
		}
		attributes.putAll(paramMap);
//		String queryString = request.getQueryString();
//
//		if(StringUtils.hasText(queryString)){
////			attributes.putAll(getQueryParameters(queryString));
//			attributes.putAll(paramMap);
//		}
		
		return new MultiPartInfo(attributes, files);
	}
	
	private void handleAttribute(Map<String, Object> attributes, FileItemStream item) throws IOException {
		Object currentValue = null;

		String name = item.getFieldName();
		String value = Streams.asString(item.openStream());

		if (attributes.containsKey(name)) {
			if (String[].class.isAssignableFrom(attributes.get(name).getClass())) {
				currentValue = (String[]) attributes.get(name);

			} else {
				currentValue = (String[]) ArrayUtils.add((String[]) currentValue, attributes.get(name));
			}

			attributes.put(name, (String[]) ArrayUtils.add((String[]) currentValue, value));

		} else {
			attributes.put(name, value);
		}
	}
	
	private Map<String, Object> getQueryParameters(String queryString) {
		Map<String, Object> map = new HashMap<String, Object>();

		if(StringUtils.hasText(queryString)){
			try {
				//한글 decode추가
				queryString = URLDecoder.decode(queryString.toString(), "utf-8");
				String[] paramValues = queryString.split("&");

				for (int i = 0; i < paramValues.length; ++i) {
					String[] paramValue = paramValues[i].split("=", 2);

					if(map.containsKey(paramValue[0])){
						Object fromParamValue = map.get(paramValue[0]);
						String[] toParamValue = null;

						if(String[].class.isAssignableFrom(fromParamValue.getClass())){
							toParamValue = (String[]) ArrayUtils.add((Object[]) fromParamValue, paramValue[1]);
						} else {
							toParamValue = (String[]) ArrayUtils.add(new String[]{(String)fromParamValue}, paramValue[1]);
						}

						map.put(paramValue[0], toParamValue);
					} else {
						map.put(paramValue[0], paramValue[1]);
					}
				}
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException(e1);
			}
		}

		return map;
	}

}
