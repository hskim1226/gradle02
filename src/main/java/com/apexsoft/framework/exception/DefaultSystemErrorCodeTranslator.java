package com.apexsoft.framework.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultSystemErrorCodeTranslator implements SystemErrorCodeTranslator {

	private final Map<String, String> systemErrorCodeMap;

	public DefaultSystemErrorCodeTranslator() {
		this.systemErrorCodeMap = new HashMap<String, String>();

		this.systemErrorCodeMap.put("com.apexsoft.framework", "FW");
		this.systemErrorCodeMap.put("com.apexsoft.framework.core", "FW_CORE");
		this.systemErrorCodeMap.put("com.apexsoft.framework.integration", "FW_INT");
		this.systemErrorCodeMap.put("com.apexsoft.framework.web", "FW_WEB");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.apexsoft.sac.framework.support.SystemErrorCodeTranslator#translate(java.lang.String)
	 */
	@Override
	public String translate(String packagee) {

		List<String> splitedPackageList = Arrays.asList(StringUtils.split(packagee, "."));

		String result;

		for (int index = splitedPackageList.size() - 1, min = 0; index >= min; index--) {
			result = this.systemErrorCodeMap.get(StringUtils.join(splitedPackageList.subList(0, index), "."));

			if (result != null) {
				return result;
			}
		}

		return null;
	}

}
