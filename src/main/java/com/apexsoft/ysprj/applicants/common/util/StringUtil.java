package com.apexsoft.ysprj.applicants.common.util;

import org.springframework.stereotype.Component;

/**
 * Created by hanmomhanda on 15. 2. 28.
 */
@Component
public class StringUtil {

    public static final String EMPTY_STRING = "";
    
    private static final String[] specialCharacters = {
        "+", "%20",
        "%28", "(",
        "%29", ")",
        "%5B", "[",
        "%5D", "]",
        "%7B", "{",
        "%7D", "}",
        "%60", "`",
        "%7E", "~",
        "%21", "!",
        "%40", "@",
        "%23", "#",
        "%24", "$",
        "%25", "%",
        "%5E", "^",
        "%3D", "=",
        "%2B", "+",
        "%26", "&",
        "%27", "'"
    };

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

    public static String br2Newline(String input) {
        String output = input.replace("<br/>", "\n");
        return output;
    }
    
    public static String urlEncodeSpecialCharacter(String urlEncoded) {
        for (int i = 0, len = specialCharacters.length ; i < len ;) {
            urlEncoded = urlEncoded.replace(specialCharacters[i++], specialCharacters[i++]);
        }
        return urlEncoded;
    }
}
