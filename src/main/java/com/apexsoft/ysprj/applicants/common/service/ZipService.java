package com.apexsoft.ysprj.applicants.common.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hanmomhanda on 16. 1. 19.
 */
public interface ZipService {
    File getZipFile(String targetDir, String zipFileName, List<File> fileList) throws IOException, InterruptedException;
}
