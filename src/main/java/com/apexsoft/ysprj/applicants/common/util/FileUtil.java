package com.apexsoft.ysprj.applicants.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hanmomhanda on 15. 2. 24.
 */
@Component
public class FileUtil {

    public static String getUploadDirectory(String admsNo, String userId, int applNo) {
        return new StringBuilder()
                .append(admsNo).append("/")
                .append(userId.substring(0, 1)).append("/")
                .append(userId).append("/")
                .append(String.valueOf(applNo))
                .toString();
    }

    public static String getUploadDirectoryFullPath(String baseDir, String admsNo, String userId, int applNo) {
        return new StringBuilder()
                .append(baseDir).append("/")
                .append(admsNo).append("/")
                .append(userId.substring(0, 1)).append("/")
                .append(userId).append("/")
                .append(String.valueOf(applNo))
                .toString();
    }

    public static String getUploadFileFullPath(String baseDir,String admsNo, String userId, int applNo, String fileName) {
        return new StringBuilder()
                .append(baseDir).append("/")
                .append(admsNo).append("/")
                .append(userId.substring(0, 1)).append("/")
                .append(userId).append("/")
                .append(String.valueOf(applNo)).append("/")
                .append(fileName)
                .toString();
    }

    public static String getRawMergedFileFullPath(String uploadDirFullPath, int applNo) {
        return new StringBuilder()
                .append(uploadDirFullPath).append("/")
                .append(applNo).append("-merged.pdf").toString();
    }

    public static String getNumberedMergedFileFullPath(String uploadDirFullPath, int applNo) {
        return new StringBuilder().append(uploadDirFullPath).append("/")
                .append(applNo).append("-merged-numbered-wo-slip-appl.pdf").toString();
    }

    public static String getFinalMergedFileFullPath(String uploadDirFullPath, int applNo) {
        return new StringBuilder().append(uploadDirFullPath).append("/")
                .append(applNo).append("-merged-final.pdf").toString();
    }

    public static String getSlipFileName(String userId) {
        return new StringBuilder().append("수험표_").append(userId).append(".pdf").toString();
    }

    public static String getApplicationFileName(String userId) {
        return new StringBuilder().append("지원서_").append(userId).append(".pdf").toString();
    }
}
