package com.apexsoft.ysprj.applicants.common.service;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.util.List;

/**
 * Created by hanmomhanda on 16. 1. 19.
 */
public class CommonsZipServiceImpl implements ZipService {
    @Override
    public File getZipFile(String targetDir, String zipFileName, List<File> fileList) throws IOException, InterruptedException {

        File zippedFile = new File(targetDir, zipFileName);
        OutputStream os = null;

        try {
            os = new FileOutputStream(zippedFile);
        } catch (FileNotFoundException e) {}

        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
        zos.setEncoding("UTF-8");
        ZipArchiveEntry ze;
        int length;
        byte[] buf = new byte[8 * 1024];
        FileInputStream fis = null;

        for (File file : fileList) {
            try {
                String fileName = file.getName();
                ze = new ZipArchiveEntry(fileName);
                zos.putArchiveEntry(ze);
                fis = new FileInputStream(file);
                while ((length = fis.read(buf, 0, buf.length)) >= 0) {
                    zos.write(buf, 0, length);
                }
                fis.close();
                zos.closeArchiveEntry();
            } finally {
                if (fis != null)
                    try { fis.close(); } catch (IOException e) {}
            }
        }

        try {
            zos.close();
        } finally {
            if (zos != null)
                try { zos.close(); } catch (IOException e) {}
        }

        return zippedFile;
    }
}
