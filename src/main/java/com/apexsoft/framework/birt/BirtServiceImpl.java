package com.apexsoft.framework.birt;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2014-08-01.
 */
public class BirtServiceImpl implements BirtService {
    @Autowired
    CommonDAO birtDao;

    public List<ApplicationInfo> getApplications() {
        return birtDao.queryForList("", "", ApplicationInfo.class);
    }

    public ApplicationInfo getApplication(String id) {
        return birtDao.queryForObject("", id, ApplicationInfo.class);
    }
}
