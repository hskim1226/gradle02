package com.apexsoft.ysprj.template.service;

import java.io.File;
import java.util.List;

public interface TempFileService {
    void saveFileMeta(TempFileVO tempFileVO);

    List<TempFileVO> getFiles();

    File getFile(int fileSeq);
}
