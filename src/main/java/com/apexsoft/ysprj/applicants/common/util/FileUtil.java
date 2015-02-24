package com.apexsoft.ysprj.applicants.common.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by hanmomhanda on 15. 2. 24.
 */
public class FileUtil {

    @Value("#{app['file.baseDir']}")
    private static String BASE_DIR;

    public static String getUploadDirectory(String admsNo, String userId, String applNo) {
        return new StringBuilder()
                .append(admsNo).append("/")
                .append(userId.substring(0, 1)).append("/")
                .append(userId).append("/")
                .append(applNo)
                .toString();
    }

    public static String getUploadDirectoryFullPath(String admsNo, String userId, String applNo) {
        return new StringBuilder()
                .append(BASE_DIR).append("/")
                .append(admsNo).append("/")
                .append(userId.substring(0, 1)).append("/")
                .append(userId).append("/")
                .append(applNo)
                .toString();
    }

    public static String getUploadFileFullPath(String admsNo, String userId, String applNo, String fileName) {
        return new StringBuilder()
                .append(BASE_DIR).append("/")
                .append(admsNo).append("/")
                .append(userId.substring(0, 1)).append("/")
                .append(userId).append("/")
                .append(applNo).append("/")
                .append(fileName)
                .toString();
    }
}
