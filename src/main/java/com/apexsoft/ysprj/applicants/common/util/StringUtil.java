package com.apexsoft.ysprj.applicants.common.util;

import org.springframework.stereotype.Component;

/**
 * Created by hanmomhanda on 15. 2. 28.
 */
@Component
public class StringUtil {

    public static final String EMPTY_STRING = "";

    public static String getEmptyIfNull(String str) {
        return str == null ? EMPTY_STRING : str;
    }

    public static String removeHyphen(String str) {
        return str == null ? EMPTY_STRING : str.replaceAll("-", EMPTY_STRING);
    }

    public static String reverseSlashToSlash(String str) {
        return str == null ? EMPTY_STRING :str.replaceAll("\\\\", "/");
    }

    public static String insertHyphenAt(String str, int index) { return str == null ? EMPTY_STRING : str.substring(0, index) + "-" + str.substring(index); }
}
