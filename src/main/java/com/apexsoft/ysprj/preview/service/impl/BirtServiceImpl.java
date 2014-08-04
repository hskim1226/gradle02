package com.apexsoft.ysprj.preview.service.impl;

import com.apexsoft.ysprj.preview.service.ApplicationInfo;
import com.apexsoft.ysprj.preview.service.BirtService;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014-08-01.
 */
@Service
public class BirtServiceImpl implements BirtService {

    private final static String NAME_SPACE = "APP.";

    @Autowired
    private CommonDAO birtDao;

    public List<ApplicationInfo> getApplications() {
        return birtDao.queryForList(NAME_SPACE + "selectList", null, ApplicationInfo.class);
//        List<ApplicationInfo> result = new ArrayList<ApplicationInfo>();
//        ApplicationInfo info = new ApplicationInfo();
//        result.add(info);
//        return result;
    }

    public ApplicationInfo getApplication(String id) {
        return birtDao.queryForObject("", id, ApplicationInfo.class);
    }
}
