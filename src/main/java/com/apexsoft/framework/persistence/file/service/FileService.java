package com.apexsoft.framework.persistence.file.service;

import com.apexsoft.framework.persistence.file.model.FileVO;

import java.io.File;
import java.util.List;

public interface FileService {
    void saveFileMeta(FileVO fileVO);

    List<FileVO> getFiles();

    File getFile(int fileSeq);
}
