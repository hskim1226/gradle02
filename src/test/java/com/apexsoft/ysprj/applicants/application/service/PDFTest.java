package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.util.FilePathUtil;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by hanmomhanda on 16. 1. 19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {
        "file:../../../../../../../../main/resources/spring/test-config.xml"
})
@ActiveProfiles("dev")
public class PDFTest {

    @Value("#{app['file.baseDir']}")
    private String fileBaseDir;

    @Value("#{app['s3.bucketName']}")
    private String s3BucketName;

    @Value("#{app['file.midPath']}")
    private String s3MidPath;

    @Value("#{app['s3.storageClass']}")
    private String s3StorageClass;

    private Application application;
    private String targetPath;
    private File[] files;
    private List<File> fileList;

    @Before
    public void before() {
        application = new Application();
        application.setApplNo(4119);
        application.setUserId("Abc333");
        application.setAdmsNo("16A");
        application.setEntrYear("2016");
        application.setAdmsTypeCode("A");
        application.setApplStsCode("00004");
        application.setDocChckYn("on");

        targetPath = FilePathUtil.getUploadDirectoryFullPath(fileBaseDir, s3MidPath, application.getAdmsNo(), application.getUserId(), application.getApplNo());
        files = new File(targetPath).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".pdf");
            }
        });
        fileList = new ArrayList<>();
        for (File file : files) {
            fileList.add(file);
        }
    }

    @Test
    public void commonsCompressionZipFileName() {
        String applId = application.getApplId();
        String zipFileName = (StringUtils.isEmpty(applId) ? String.valueOf(application.getApplNo()) : applId) + ".zip";
        File zippedFile = new File(targetPath, zipFileName);
        OutputStream os = null;

        try {
            os = new FileOutputStream(zippedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
//        String defaultCharsetName = Charset.defaultCharset().name();
//        zos.setEncoding(defaultCharsetName);
//        zos.setFallbackToUTF8(true);
//        zos.setUseLanguageEncodingFlag(true);
        ZipArchiveEntry ze;
        int length;
        byte[] buf = new byte[8 * 1024];
        FileInputStream fis = null;
        File file = fileList.get(0);

        try {
            String fileName = file.getName();
            String[] encoding = {"utf-8", "euc-kr", "ksc5601", "x-windows-949", "iso-8859-1"};
            for (int i = 0 ; i < encoding.length ; i++) {
                for (int j = 0 ; j < encoding.length ; j++) {
                    String encodedFileName = new String(fileName.getBytes(encoding[i]), encoding[j]);
System.out.println(encodedFileName);
                    ze = new ZipArchiveEntry(encodedFileName);
                    zos.putArchiveEntry(ze);
                    fis = new FileInputStream(file);
                    while ((length = fis.read(buf, 0, buf.length)) >= 0) {
                        zos.write(buf, 0, length);
                    }
                    fis.close();
                    zos.closeArchiveEntry();
                }
            }
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try { fis.close(); } catch (IOException e) {}
        }

        try {
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zos != null)
                try { zos.close(); } catch (IOException e) {}
        }
    }

    @Test
    public void systemZipFileName() throws Exception {
        File file = fileList.get(0);
        Runtime rt = Runtime.getRuntime();
System.out.println(targetPath);
//        Process p1 = rt.exec("cd " + targetPath);
//        p1.waitFor();
//        BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
//        String line = "";
//        StringBuilder sb = new StringBuilder();
//        while ((line = br.readLine()) != null) {
//            sb.append(line + "\n");
//        }
//        System.out.println(sb.toString());
        Process p2 = Runtime.getRuntime().exec("zip -j " + targetPath + "/test.zip " + targetPath + "/" + file.getName());
        p2.waitFor();
    }

    @Test
    public void javaZipFileName() throws Exception {
        // Wrap a FileOutputStream around a ZipOutputStream
        // to store the zip stream to a file. Note that this is
        // not absolutely necessary
        String targetZipFilePath = targetPath + "/test.zip";
        FileOutputStream fileOutputStream = new FileOutputStream(targetZipFilePath);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        File inputFile = fileList.get(0);

        // a ZipEntry represents a file entry in the zip archive
        // We name the ZipEntry after the original file's name
        ZipEntry zipEntry = new ZipEntry(inputFile.getName());
        zipOutputStream.putNextEntry(zipEntry);

        FileInputStream fileInputStream = new FileInputStream(inputFile);
        byte[] buf = new byte[1024];
        int bytesRead;

        // Read the input file by chucks of 1024 bytes
        // and write the read bytes to the zip stream
        while ((bytesRead = fileInputStream.read(buf)) > 0) {
            zipOutputStream.write(buf, 0, bytesRead);
        }

        // close ZipEntry to store the stream to the file
        zipOutputStream.closeEntry();

        zipOutputStream.close();
        fileOutputStream.close();

        System.out.println("Regular file :" + inputFile.getCanonicalPath()+" is zipped to archive :"+targetZipFilePath);

    }
}
