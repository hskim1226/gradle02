package com.apexsoft.ysprj.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.apexsoft.ysprj.user.control.UserAccountController;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by hanmomhanda on 15. 8. 6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource("classpath:/properties/config/app.new.properties")
@ContextConfiguration(
        locations = {
//            "file:src/main/webapp/WEB-INF/config/context-mvc.xml",
//            "classpath:/spring/context-common.xml",
            "classpath:/spring/context-crypto.xml",
            "classpath:/spring/context-environment.xml",
            "classpath:/spring/context-ehcache.xml",
            "classpath:/ehcache/ehcache.xml",
            "classpath:/mybatis/mybatis-config.xml",
            "classpath:/spring/context-repository.xml",
            "classpath:/spring/test-usercontroller.xml",
            "classpath:/spring/test-security.xml"
        }
)
@ActiveProfiles("new")
@WebAppConfiguration
@EnableWebMvc
public class UserAccountControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserAccountController())
                .addFilters(this.springSecurityFilterChain).build();

//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
//                .addFilters(this.springSecurityFilterChain).build();
    }

    @Test
    public void loginForm() throws Exception {
        mockMvc.perform(post("/user/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void login() throws Exception {
        final String userName = "Abc555", password = "Abc55555";

        mockMvc
                .perform(post("/j_spring_security_check")
                        .param("j_username", userName)
                        .param("j_password", password))
                .andDo(print())
                .andExpect(redirectedUrl("/"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        Enumeration enumeration = session.getAttributeNames();
                        while (enumeration.hasMoreElements()) {
                            System.err.println(enumeration.nextElement());
                        }

                        SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
                        Assert.assertEquals(securityContext.getAuthentication().getName(), userName);
                    }
                });
    }
}
