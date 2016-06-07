package com.apexsoft.ysprj.user;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.security.UserDetailsServiceImpl;
import com.apexsoft.framework.security.UserSessionVO;
import com.apexsoft.ysprj.user.control.UserAccountController;
import com.apexsoft.ysprj.user.domain.User;
import com.apexsoft.ysprj.user.service.UserAccountService;
import com.apexsoft.ysprj.user.validator.UserModValidator;
import com.apexsoft.ysprj.user.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hanmomhanda on 15. 8. 6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)
@PropertySource("classpath:/properties/config/app.dev.properties")
@ContextConfiguration(
        locations = {
//            "file:src/main/webapp/WEB-INF/config/context-mvc.xml",
//            "classpath:/spring/context-common.xml",
                "classpath:/spring/test/test-usercontroller.xml",
//            "classpath:/spring/context-crypto.xml",
//            "classpath:/spring/context-environment.xml",
//            "classpath:/spring/context-ehcache.xml",
//            "classpath:/ehcache/ehcache.xml",
//            "classpath:/mybatis/mybatis-config.xml",
//            "classpath:/spring/context-repository.xml",
                "classpath:/spring/test/test-security.xml"
        }
)
@ActiveProfiles("dev")
@WebAppConfiguration
@EnableWebMvc
public class UserAccountControllerTest {

//    @Autowired
//    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserAccountService userAccountService;

    @Mock
    private CommonDAO commonDAO;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserModValidator userModValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserAccountController userAccountController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userAccountController)
                .addFilters(this.springSecurityFilterChain)
                  .build();


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
        final String userId = "Abc555", password = "Abc55555",
                userName = "MYUNG WOON OH",
                encryptedPassword = "$2a$10$g4syovyJk1Ul1P9qcB1GD.D66xl04boRgLxAAnWTYEM42oyQltR/i",
                email = "hanmomhanda@gmail.com";

//        User user = new User();
//        user.setUserId(userId);
//        user.setName(userName);
//        user.setPswd(encryptedPassword);
//        user.setMailAddr(email);
//        user.setEnabled(true);
//        when(userAccountService.retrieveUser(userId)).thenReturn(user);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        when(userAccountService.retrieveAuthorities(userId)).thenReturn(authorities);

        UserSessionVO userSessionVO = new UserSessionVO();
        userSessionVO.setUsername( userId );
        userSessionVO.setName( userName );
        userSessionVO.setPassword( encryptedPassword );
        userSessionVO.setEnabled( true );
        userSessionVO.setEmail( email );
        userSessionVO.setAuthorities(authorities);
        when(userDetailsService.loadUserByUsername(userId)).thenReturn(userSessionVO);

        mockMvc
//                .perform(post("/j_spring_security_check") // Spring Security 4에서 login으로 변경
                .perform(post("/login")
                        .param("j_username", userId)
                        .param("j_password", password))
                .andDo(print())
//                .andExpect(redirectedUrl("/"))
                .andExpect(new ResultMatcher() {
                    public void match(MvcResult mvcResult) throws Exception {
                        HttpSession session = mvcResult.getRequest().getSession();
                        Enumeration enumeration = session.getAttributeNames();
                        while (enumeration.hasMoreElements()) {
                            System.err.println(enumeration.nextElement());
                        }

//                        SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
//                        assertEquals(securityContext.getAuthentication().getName(), userId);
                    }
                });

        verify(userDetailsService).loadUserByUsername(userId);
    }
}
