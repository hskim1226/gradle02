package com.apexsoft.framework.persistence.file.receiver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.apexsoft.framework.persistence.file.exception.FileUploadException;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.persistence.file.model.MultiPartInfo;

/**
 *
 *  {@link MultiPartReceiver} 의 구현체
 *
 */
public class MultiPartReceiverImpl implements MultiPartReceiver {
	private static final Logger logger = LoggerFactory.getLogger(MultiPartReceiverImpl.class);

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

		DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = (DefaultMultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> fileMap = defaultMultipartHttpServletRequest.getMultiFileMap();
		Map<String, String[]> paramMap = defaultMultipartHttpServletRequest.getParameterMap();

		Map<String, Object> attributes = getQueryParams(paramMap);
		List<FileItem> files = new ArrayList<FileItem>();

		Set<Map.Entry<String, List<MultipartFile>>> entrySet = fileMap.entrySet();
		for (Map.Entry<String, List<MultipartFile>> entry : entrySet) {

			File f = File.createTempFile("upload", "temp");
			f.deleteOnExit();

			OutputStream fos = new FileOutputStream(f);
			List<MultipartFile> fileList = entry.getValue();
			for (MultipartFile file : fileList) {
                org.apache.commons.fileupload.FileItem fileItem = ((CommonsMultipartFile) file).getFileItem();
				IOUtils.copyLarge(file.getInputStream(), fos);
				files.add(new FileItem(entry.getKey(), fileItem.getName(), f));
				// 20161101 go2zo, Comma suffiex 에러 임시
				if (fileItem.getName() != null && fileItem.getName().endsWith(",")) {
					logger.error("Comma suffixed: {FieldName:{}, FileName:{}}", new Object[] {entry.getKey(), fileItem.getName() });
				}
			}
		}

		return new MultiPartInfo(attributes, files);
	}

    private Map<String, Object> getQueryParams(Map<String, String[]> paramMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Map.Entry<String, String[]>> entrySet = paramMap.entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue()[0]);
        }
        return map;
    }

//	private void handleAttribute(Map<String, Object> attributes, FileItemStream item) throws IOException {
//		Object currentValue = null;
//
//		String name = item.getFieldName();
//		String value = Streams.asString(item.openStream());
//
//		if (attributes.containsKey(name)) {
//			if (String[].class.isAssignableFrom(attributes.get(name).getClass())) {
//				currentValue = (String[]) attributes.get(name);
//
//			} else {
//				currentValue = (String[]) ArrayUtils.add((String[]) currentValue, attributes.get(name));
//			}
//
//			attributes.put(name, (String[]) ArrayUtils.add((String[]) currentValue, value));
//
//		} else {
//			attributes.put(name, value);
//		}
//	}
//
//	private Map<String, Object> getQueryParameters(String queryString) {
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		if(StringUtils.hasText(queryString)){
//			try {
//				//한글 decode추가
//				queryString = URLDecoder.decode(queryString.toString(), "utf-8");
//				String[] paramValues = queryString.split("&");
//
//				for (int i = 0; i < paramValues.length; ++i) {
//					String[] paramValue = paramValues[i].split("=", 2);
//
//					if(map.containsKey(paramValue[0])){
//						Object fromParamValue = map.get(paramValue[0]);
//						String[] toParamValue = null;
//
//						if(String[].class.isAssignableFrom(fromParamValue.getClass())){
//							toParamValue = (String[]) ArrayUtils.add((Object[]) fromParamValue, paramValue[1]);
//						} else {
//							toParamValue = (String[]) ArrayUtils.add(new String[]{(String)fromParamValue}, paramValue[1]);
//						}
//
//						map.put(paramValue[0], toParamValue);
//					} else {
//						map.put(paramValue[0], paramValue[1]);
//					}
//				}
//			} catch (UnsupportedEncodingException e1) {
//				throw new RuntimeException(e1);
//			}
//		}
//
//		return map;
//	}

}
