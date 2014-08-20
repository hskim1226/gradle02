package com.apexsoft.ysprj.user.test;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    CommonDAO commonDAO;

    @Override
    public List<EntireApplication> retrieveEntireApplication(String applNo) {
        List<EntireApplication> entireApplicationList = null;

        try {
            entireApplicationList = commonDAO.queryForList("com.apexsoft.ysprj.test.entireApplication.Mapper.selectEntireApplicationByApplNo", applNo, EntireApplication.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entireApplicationList;
    }

    @Override
    public JoinedApplication retrieveJoinedApplication() {
        JoinedApplication ja = null;
        try {
            ja = commonDAO.queryForObject("com.apexsoft.ysprj.test.joinedApplication.Mapper.selectByApplNo", JoinedApplication.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ja;
    }

    @Override
    public Application retrieveApplication() {
        Application app = null;
        try {
            app = commonDAO.queryForObject("com.apexsoft.ysprj.test.joinedApplication.Mapper.selectApplByApplNo", JoinedApplication.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return app;
    }
}
