package com.apexsoft.framework.web.file.receiver;

import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.web.file.exception.UploadException;
import com.apexsoft.framework.web.file.model.MultiPartInfo;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			throw new UploadException("error receiving file upload request", e);
		}
	}

	private MultiPartInfo receiveInternal(HttpServletRequest request) throws Exception {
		ServletFileUpload upload = new ServletFileUpload();

		FileItemIterator iter = upload.getItemIterator(request);
		Map<String, Object> attributes = new HashMap<String, Object>();
		List<FileItem> files = new ArrayList<FileItem>();

		while (iter.hasNext()) {
			FileItemStream itemStream = iter.next();

			if (itemStream.isFormField()) {
				handleAttribute(attributes, itemStream);
			} else {
				File f = File.createTempFile("upload", "temp");
				f.deleteOnExit();
				
				OutputStream fos = new FileOutputStream(f);
				IOUtils.copyLarge(itemStream.openStream(), fos);
				
				files.add(new FileItem(itemStream.getFieldName(), itemStream.getName(), f));
			}
		}
		
		String queryString = request.getQueryString();
		
		if(StringUtils.hasText(queryString)){
			attributes.putAll(getQueryParameters(queryString));
		}
		
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
