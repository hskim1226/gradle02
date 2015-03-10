package com.apexsoft.ysprj.applicants.common.util;

import org.springframework.stereotype.Component;

/**
 * Created by hanmomhanda on 15. 2. 28.
 */
@Component
public class StringUtil {

    public static String getEmptyIfNull(String str) {
        return str == null ? "" : str;
    }

    public static String removeHyphen(String str) {
        return str == null ? "" : str.replaceAll("-", "");
    }

    public static String reverseSlashToSlash(String str) {
        return str == null ? "" :str.replaceAll("\\\\", "/");
    }

    public static String insertHyphenAt(String str, int index) { return str == null ? "" : str.substring(0, index) + "-" + str.substring(index); }
}
