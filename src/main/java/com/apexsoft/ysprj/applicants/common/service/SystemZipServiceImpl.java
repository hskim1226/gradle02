package com.apexsoft.ysprj.applicants.common.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hanmomhanda on 16. 1. 19.
 */
@Service
public class SystemZipServiceImpl implements ZipService{
    @Override
    // Runtime.exec()는 따옴표 있는 args를 인식하지 않는 문제 있음
    public File getZipFile(String targetDir, String zipFileName, List<File> fileList)
            throws IOException, InterruptedException {
        StringBuilder srcFileNames = new StringBuilder();
        String targetZipFileFullPath = "'" + targetDir + "/" + zipFileName + "'";
//        String targetZipFileFullPath = targetDir + "/" + zipFileName;
        for (File file : fileList) {
            srcFileNames.append("'").append(targetDir).append("/").append(file.getName()).append("' ");
//            srcFileNames.append(targetDir).append("/").append(file.getName()).append(" ");
        }
        String srcFileNamesTrimmed = srcFileNames.toString().trim();
        Process p2 = Runtime.getRuntime().exec("zip -j " + targetZipFileFullPath + " " + srcFileNamesTrimmed);

//        Process p2 = Runtime.getRuntime().exec(new String[]{"zip", "-j", targetZipFileFullPath, srcFileNamesTrimmed});
        p2.waitFor();
        return new File(targetZipFileFullPath);
    }

    @Override
    public File appendFilesToZipFile(List<File> files, File zipFile) throws IOException, InterruptedException {
        return null;
    }
}
