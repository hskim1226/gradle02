package com.apexsoft.ysprj.applicants.common.util;

import com.apexsoft.framework.exception.YSBizException;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hanmomhanda on 15. 8. 12.
 */
public class CryptoUtil {

    public static String getCryptedString(ServletContext context, String input, boolean isEncrypt) throws IOException {
        Properties prop = new Properties();
        InputStream is = context.getResourceAsStream("WEB-INF/grad-ks");
        String result = null;

        try {
            prop.load(is);
            TextEncryptor textEncryptor = Encryptors.queryableText(prop.getProperty("ENC_PSWD"), prop.getProperty("ENC_SALT"));
            result = isEncrypt ? textEncryptor.encrypt(input) : textEncryptor.decrypt(input);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new YSBizException(e);
            }
        }
        return result;
    }
}
