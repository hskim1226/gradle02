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
}
