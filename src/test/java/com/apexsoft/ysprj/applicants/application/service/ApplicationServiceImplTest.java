package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.unused.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by hanmomhanda on 14. 8. 28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:../../../../../../../../src/main/webapp/WEB-INF/config/context-mvc.xml",
        "classpath*:/spring/context-persistence.xml"
})
public class ApplicationServiceImplTest {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.applicants.application.sqlmap.";

    @Autowired
    ApplicationService applicationService;

    @Autowired
    CommonDAO commonDAO;

    @Test
    public void insertApplication() {
        Application application = new Application();
        application.setApplNo(2);
        application.setUserId("test");
        application.setAdmsNo("15-A");
        assertEquals(createApplication(application), 1, 0);
    }

    private int createApplication(Application application) {
        return commonDAO.insert(NAME_SPACE + "ApplicationMapper.insertSelective", application);
    }

}