package com.apexsoft.ysprj.applicants.common.service;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.Deflater;

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
        zos.setUseLanguageEncodingFlag(true);
        zos.setFallbackToUTF8(true);
        zos.setLevel(Deflater.NO_COMPRESSION);
        ZipArchiveEntry ze;
        int length;
        byte[] buf = new byte[8 * 1024];
        FileInputStream fis = null;

        // 새 zip 파일에 파일 추가
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

    @Override
    public File appendFilesToZipFile(List<File> fileList, File zipFile) throws IOException, InterruptedException {
        OutputStream os = null;
        String zipFilePath = zipFile.getAbsolutePath();
        String zipFileDir = zipFilePath.substring(0, zipFilePath.lastIndexOf("/"));
        File tempZipFile = new File(zipFileDir, "temp.zip");
        FileUtils.copyFile(zipFile, tempZipFile);
        zipFile.delete();

        try {
            os = new FileOutputStream(new File(zipFilePath));
        } catch (FileNotFoundException e) {}

        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
        zos.setEncoding("UTF-8");
        zos.setUseLanguageEncodingFlag(true);
        zos.setFallbackToUTF8(true);
        zos.setLevel(Deflater.NO_COMPRESSION);
        ZipArchiveEntry ze;
        int length;
        byte[] buf = new byte[8 * 1024];
        FileInputStream fis = null;

        // 기존 zip 파일에 있던 파일을 새 zip 파일에 추가
        ZipFile zFile = new ZipFile(tempZipFile);
        Enumeration<ZipArchiveEntry> enumZe = zFile.getEntries();
        InputStream is = null;
        while(enumZe.hasMoreElements()) {
            ze = enumZe.nextElement();
            try {
                is = zFile.getInputStream(ze);
                zos.putArchiveEntry(ze);
                while ((length = is.read(buf, 0, buf.length)) >= 0) {
                    zos.write(buf, 0, length);
                }
                zos.closeArchiveEntry();
            } finally {
                if (is != null)
                    try { is.close(); } catch (IOException e) {}
            }
        }

        // 추가할 새 파일을 새 zip 파일에 추가
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

        if (tempZipFile.exists()) tempZipFile.delete();

        return zipFile;
    }
}
