package com.apexsoft.framework.persistence.file.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.file.model.FileVO;
import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocumentKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 26.
 * Time: 오후 6:32
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FileServiceImpl implements FileService {

    private static String NAME_SPACE = "TEMP_FILE.";
    private final static String FILE_NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public void saveFileMeta(FileVO tempFileVO) {
        commonDAO.insert(NAME_SPACE+"insert", tempFileVO);
    }

    @Override
    public List<FileVO> getFiles() {
        return commonDAO.queryForList(NAME_SPACE+"selectList", null , FileVO.class);
    }

    @Override
    public File getFile(int fileSeq) {
        FileVO tempFileVO = commonDAO.queryForObject(NAME_SPACE+"selectByPk", fileSeq, FileVO.class);
        return new File(tempFileVO.getPath(), tempFileVO.getFileName());
    }

    @Override
    public File getFile(ApplicationDocumentKey applicationDocumentKey) {
        FileVO fileVO = commonDAO.queryForObject(FILE_NAME_SPACE + "ApplicationDocumentMapper.selectByPrimaryKey",
                                                 applicationDocumentKey,
                                                 FileVO.class);
        return new File(fileVO.getPath(), fileVO.getFileName());
    }

    @Override
    public List<FileVO> getFiles(int applNo) {
        return commonDAO.queryForList(FILE_NAME_SPACE + "CustomApplicationDocumentMapper.selectByApplNo",
                                      applNo,
                                      FileVO.class);
    }
}
