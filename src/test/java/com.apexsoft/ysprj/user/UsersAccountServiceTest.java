package com.apexsoft.ysprj.user;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.user.domain.User;
import com.apexsoft.ysprj.user.service.UserAccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-08-21
 * Time: 오후 3:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:/properties/config/app.dev.properties")
@ContextConfiguration(locations = {
//        "file:src/main/webapp/WEB-INF/config/context-mvc.xml",
        "classpath:/spring/test/test-config.xml"
//        "classpath:/spring/test/test-usercontroller.xml"
//        "classpath*:/spring/context-persistence.xml"
})
@ActiveProfiles("dev")
public class UsersAccountServiceTest {

    @Mock
    CommonDAO dao;

    @Autowired
    @InjectMocks
    private UserAccountService userAccountService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testRetrieveUserId() {
        User user = new User();
        user.setName("삼돌이");

        ExecutionContext<Object> ec = new ExecutionContext<>();

        when(dao.queryForObject(any(String.class), any(User.class), eq(User.class))).thenReturn(user);
        ExecutionContext c = userAccountService.retrieveUserId(user);
        Assert.assertNotNull(c.getData());
        printObject(c.getData());
    }

    private void printObject(Object obj) {
        StringBuffer sb = new StringBuffer();
        if( obj instanceof List ) {
            List list = ((List) obj);
            sb.append("{");
            for( int i = 0; i < list.size(); i++ ) {
                sb.append(list.get(i));
                if(i < list.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("}");
        } else if( obj instanceof String[] ) {
            String[] arr = (String[]) obj;
            sb.append("{");
            for( int i = 0; i < arr.length; i++ ) {
                sb.append(arr[i]);
                if(i < arr.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("}");
        }
    }

}
