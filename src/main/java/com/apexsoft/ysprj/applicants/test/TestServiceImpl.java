package com.apexsoft.ysprj.applicants.test;

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

    /**
     * 1:N 관계인 테이블들을 모두 조인하여 하나의 쿼리로 실행하면
     * ResultMap에 1:N으로 들어오지 않는다.
     * 이럴 경우 1:N 인 테이블은 중첩쿼리로 실행해야 ResultMap이 제대로 구성됨
     *
     * @param applNo
     * @return
     */
    @Override
    public List<EntireApplication> retrieveEntireApplicationByOneQuery(String applNo) {
        List<EntireApplication> entireApplicationList = null;

        try {
            entireApplicationList = commonDAO.queryForList("com.apexsoft.ysprj.test.entireApplication.Mapper.selectEntireApplicationByApplNo", applNo, EntireApplication.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entireApplicationList;
    }

    /**
     * 1:N 인 테이블을 중첩쿼리로 ResultMap을 구성한 예
     * 
     * @param applNo
     * @return
     */
    @Override
    public List<EntireApplication> retrieveEntireApplicationByNestedQuery(String applNo) {
        List<EntireApplication> entireApplicationList = null;

        try {
            entireApplicationList = commonDAO.queryForList("com.apexsoft.ysprj.test.entireApplication.Mapper.selectEntireApplicationByApplNoNestedSelect", applNo, EntireApplication.class);
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
