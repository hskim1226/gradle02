package com.apexsoft.ysprj.applicants.common.util;

import com.apexsoft.framework.exception.YSBizException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hanmomhanda on 16. 1. 12.
 */
public class StreamUtil {

    public static ByteArrayOutputStream getBaosFromInputStream(InputStream inputStream) {
        ByteArrayOutputStream baos;
        try {
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
        } catch (IOException e) {
            throw new YSBizException(e);
        }
        return baos;
    }
}
