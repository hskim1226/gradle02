package com.apexsoft.framework.persistence.file.service;

import com.apexsoft.framework.persistence.file.model.FileVO;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocumentKey;
import com.apexsoft.ysprj.applicants.application.domain.ParamForApplicationDocument;

import java.io.File;
import java.util.List;

@Deprecated
public interface FileService {
    void saveFileMeta(FileVO fileVO);

    List<FileVO> getFiles();

    File getFile(int fileSeq);

    File getFile(ApplicationDocumentKey applicationDocumentKey);
    File getFile(ParamForApplicationDocument paramForApplicationDocument);
    List<FileVO> getFiles(int applNo);
}
