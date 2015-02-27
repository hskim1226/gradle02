package com.apexsoft.ysprj.user;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.domain.User;
import com.apexsoft.ysprj.user.service.UserAccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: go2zo
 * Date: 2014-08-21
 * Time: 오후 3:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/context-*.xml",
        "classpath*:/spring/context-*.xml"
})
public class UsersAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;

    @Test
    public void testRetrieveUserId() {
        User user = new User();
        user.setName("홍길동");
//        user.setMailAddr("go2zo@apexsoft.co.kr");
        ExecutionContext c = userAccountService.retrieveUserIds(user, 3);
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
