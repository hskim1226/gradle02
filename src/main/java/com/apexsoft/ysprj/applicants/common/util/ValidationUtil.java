package com.apexsoft.ysprj.applicants.common.util;

import com.apexsoft.ysprj.admin.control.form.ChangeInfoForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hanmomhanda on 15. 3. 6.
 */
public class ValidationUtil {

    public static boolean checkKorSSN(String ssn) {
        boolean isValid = true;
        int len = ssn.length();
        if (len != 13) {
            isValid = false;
        } else {
            Pattern p = Pattern.compile("^\\d{6}[1-4]\\d{6}$");
            if (!p.matcher(ssn).matches()) {
                isValid = false;
            } else {
                int sum = 0;
                for (int i = 0 ; i < len - 1 ; i++) {
                    sum += Character.getNumericValue(ssn.charAt(i)) * (i < 8 ? i + 2 : i - 6);
                }
                sum = (11 - sum % 11) % 10;
                if (sum != Character.getNumericValue(ssn.charAt(len - 1))) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }
}
