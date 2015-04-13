package com.apexsoft.ysprj.applicants.common.util;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hanmomhanda on 15. 2. 24.
 */
@Component
public class FileUtil {

    private static final String SLASH = "/";
    private static final String DOUBLE_SLASH = "//";
    private static final String MERGED_PDF = "-merged.pdf";
    private static final String NUMBERED_PDF_WITHOUT_SLIP = "-merged-numbered-wo-slip-appl.pdf";
    private static final String MERGED_FINAL_PDF = "-merged-final.pdf";
    private static final String ALL_FILE_PDF = "-all-fle.pdf";
    private static final String APPLICATION_SLIP_PREFIX = "application_slip_";
    private static final String APPLICATION_FORM_PREFIX = "application_form_";
    private static final String ENCODED_AMP = "&amp;";
    private static final String RECOVERED_AMP = "&";


    public static String getUploadDirectory(String admsNo, String userId, int applNo) {
        return new StringBuilder()
                .append(admsNo).append("/")
                .append(userId.substring(0, 1)).append("/")
                .append(userId).append("/")
                .append(String.valueOf(applNo))
                .toString();
    }

    public static String getUploadDirectoryFullPath(String baseDir, String s3MidPath, String admsNo, String userId, int applNo) {
        return new StringBuilder()
                .append(baseDir).append("/")
                .append(s3MidPath).append("/")
                .append(admsNo).append("/")
                .append(userId.substring(0, 1)).append("/")
                .append(userId).append("/")
                .append(String.valueOf(applNo))
                .toString();
    }

    public static String replaceDoubleSlashToSingleSlash(String path) {
        return path.replace(DOUBLE_SLASH, SLASH);
    }

    public static String getRawMergedFileFullPath(String uploadDirFullPath, int applNo) {

        return new StringBuilder()
                .append(replaceDoubleSlashToSingleSlash(uploadDirFullPath)).append("/")
                .append(applNo).append("-merged.pdf").toString();
    }

    public static String getNumberedMergedFileFullPath(String uploadDirFullPath, int applNo) {
        return new StringBuilder()
                .append(replaceDoubleSlashToSingleSlash(uploadDirFullPath)).append("/")
                .append(applNo).append("-merged-numbered-wo-slip-appl.pdf").toString();
    }

    public static String getFinalMergedFileFullPath(String uploadDirFullPath, int applNo) {
        return new StringBuilder()
                .append(uploadDirFullPath).append("/")
                .append(applNo).append("-merged-final.pdf").toString();
    }

    public static String getFinalUserDownloadFileName(String userId) {
        return new StringBuilder()
                .append(userId).append("-all-file.pdf").toString();
    }

    public static String getSlipFileName(String userId) {
        return new StringBuilder()
                .append("application_slip_").append(userId).append(".pdf").toString();
    }

    public static String getApplicationFileName(String userId) {
        return new StringBuilder()
                .append("application_form_").append(userId).append(".pdf").toString();
    }

    public static String getS3PathFromLocalFullPath(String localFullPath, String baseDir) {
        return localFullPath.substring(baseDir.length() + 1);
    }

    public static String removeSlash(String path) {
        return path.replace('/', '-');
    }

    public static String encodeSlash(String path, String temp) {
        return path.replace("/", temp);
    }

    public static String decodeSlash(String path, String temp) {
        return path.replace(temp, "/");
    }

    public static String encodeColonSlash(String path, String temp) {
        int posColon = path.indexOf(':');
        if ( posColon > 0) {
            String windowPos = path.substring(posColon);
            String encoded = windowPos.replace("/", temp);
            return path.substring(0, posColon) + encoded;
        }
        return path;
    }

    public static String recoverAmpersand(String path) {
        return path.replace(ENCODED_AMP, RECOVERED_AMP);
    }

    public static String getFinalMergedFileFullPath(String bucketName, String midPath, Application appl) {
        int applNo = appl.getApplNo();
        String admsNo = appl.getAdmsNo();
        String userId = appl.getUserId();
        String pathToPdfDir = new StringBuilder()
                .append(bucketName)
                .append(SLASH)
                .append(midPath)
                .append(SLASH)
                .append(admsNo)
                .append(SLASH)
                .append(userId.substring(0, 1))
                .append(SLASH)
                .append(userId)
                .append(SLASH)
                .append(String.valueOf(applNo))
                .append(SLASH)
                .append(applNo)
                .append("-merged-final.pdf")
                .toString();
        return pathToPdfDir;
    }
}
