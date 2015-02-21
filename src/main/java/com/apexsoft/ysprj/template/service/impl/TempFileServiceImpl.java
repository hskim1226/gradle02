package com.apexsoft.ysprj.template.service.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.template.service.TempFileService;
import com.apexsoft.ysprj.template.service.TempFileVO;
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
public class TempFileServiceImpl implements TempFileService {

    private static String NAME_SPACE = "TEMP_FILE.";
    @Autowired
    private CommonDAO commonDAO;

    @Override
    public void saveFileMeta(TempFileVO tempFileVO) {
        commonDAO.insert(NAME_SPACE+"insert", tempFileVO);
    }

    @Override
    public List<TempFileVO> getFiles() {
        return commonDAO.queryForList(NAME_SPACE+"selectList", null , TempFileVO.class);
    }

    @Override
    public File getFile(int fileSeq) {
        TempFileVO tempFileVO = commonDAO.queryForObject(NAME_SPACE+"selectByPk", fileSeq, TempFileVO.class);
        return new File(tempFileVO.getPath(), tempFileVO.getFileName());
    }
}
