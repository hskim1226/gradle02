package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.ErrorInfo;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.handler.RowHandler;
import com.apexsoft.ysprj.applicants.common.util.StringUtil;
import com.apexsoft.ysprj.code.AuthorityType;
import com.apexsoft.ysprj.user.domain.Authorities;
import com.apexsoft.ysprj.user.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private static String NAME_SPACE="com.apexsoft.ysprj.user.sqlmap.";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public ExecutionContext registerUserAndAuthority(User user) {
        ExecutionContext ec = new ExecutionContext();
        int rUserInsert = 0, rAuthInsert = 0;

        user.setEnabled( true );

        rUserInsert = commonDAO.insert( NAME_SPACE + "insertUser", user);

        Authorities authVO = new Authorities();
        authVO.setUsername(user.getUserId());
        authVO.setAuthority(AuthorityType.ROLE_USER.getValue());
        rAuthInsert = commonDAO.insert(NAME_SPACE+"insertAuthority", authVO);

        if ( rUserInsert == 1 && rAuthInsert == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U103"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U104"));
            String errCode = null;
            if ( rUserInsert != 1 ) errCode = "ERR1001";
            if ( rAuthInsert != 1 ) errCode = "ERR1002";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }
        return ec;
    }

    @Override
	public User retrieveUser(String userName) {
        User user = commonDAO.queryForObject(NAME_SPACE+"selectByPk", userName, User.class);

		return user;
	}

    @Override
    public List<User> retrieveEmails(String email) {
    	List<User> users = commonDAO.queryForList(NAME_SPACE+"selectByEmail", email, User.class);

    	return users;
    }

    @Override
    public ExecutionContext retrieveUserId(User user) {
        ExecutionContext ec = new ExecutionContext();
        User result = commonDAO.queryForObject(NAME_SPACE + "findUserId", user, User.class);
        if (result != null) {
            ec.setResult(ExecutionContext.SUCCESS);
            Map<String, Object> ecDataMap = new HashMap<String, Object>();
            ecDataMap.put("userId", result.getUserId());
            ec.setData(ecDataMap);
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U501"));
        }
        return ec;
    }

    @Override
    public ExecutionContext retrievePwdLink(User user) {
        ExecutionContext ec = new ExecutionContext();
        User result = commonDAO.queryForObject(NAME_SPACE + "findUserId", user, User.class);
        if (result != null) {
            ec.setResult(ExecutionContext.SUCCESS);
            String shaStr = DigestUtils.shaHex(new StringBuilder().append(result.getUserId()).append(result.getMailAddr()).toString());
            Map<String, Object> ecDataMap = new HashMap<String, Object>();
            ecDataMap.put("link", shaStr);
            ec.setData(ecDataMap);
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U502"));
        }
        return ec;
    }


    @Override
    public Integer changePassword(User user) {
        return commonDAO.update(NAME_SPACE + "changePasswd", user);
    }

    @Override
    public List<SimpleGrantedAuthority> retrieveAuthorities(String userName) {
        return commonDAO.queryWithResultHandler(NAME_SPACE+"selectAuthorities", userName, new RowHandler<Authorities, SimpleGrantedAuthority>() {
            @Override
            public SimpleGrantedAuthority handleRow(Authorities authorities) {
                return new SimpleGrantedAuthority(authorities.getAuthority());
            }
        });
    }

    @Override
    public ExecutionContext isUserIdAvailable(User user) {
        ExecutionContext context = new ExecutionContext();
        context.setMessage( MessageResolver.getMessage("U00131") );  // 사용 가능한 ID 입니다.

        if( retrieveUser( user.getUserId() ) != null ) {
            context.setResult( ExecutionContext.FAIL );
            context.setMessage( MessageResolver.getMessage("U00132"));  // 이미 존재하는 ID 입니다.
        }
        return context;
    }

    @Override
    public ExecutionContext isEmailAvailable(User user) {
    	ExecutionContext context = new ExecutionContext();
    	context.setMessage( MessageResolver.getMessage("U00136") );  // 사용 가능한 ID 입니다.

    	List<User> retrieveEmail = retrieveEmails( user.getMailAddr() );
    	if( retrieveEmail != null && retrieveEmail.size() > 0 ) {
    		context.setResult( ExecutionContext.FAIL );
    		context.setMessage( MessageResolver.getMessage("U00137"));  // 이미 존재하는 ID 입니다.
    	}
    	return context;
    }


    @Override
    public ExecutionContext modifyUser( User user) {
        ExecutionContext ec = new ExecutionContext();

        user.setModId(user.getUserId());
        user.setModDate(new Date());

        int u1 = commonDAO.update(NAME_SPACE + "updateUserByPrimaryKeySelective", user);

        if (u1 == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(MessageResolver.getMessage("U106"));
            user.setMobiNum(StringUtil.removeHyphen(user.getMobiNum()));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U107"));
            ec.setErrCode("ERRU001");
            Map<String, String> errorInfo = new HashMap<String, String>();
            errorInfo.put("userId", user.getUserId());
            ec.setErrorInfo(new ErrorInfo(errorInfo));
            throw new YSBizException(ec);
        }
        return ec;
    }

    @Override
    public ExecutionContext checkPwd(User user) {
        ExecutionContext ec = new ExecutionContext();
        User userFromDB = retrieveUser(user.getUserId());
        userFromDB.setMobiNum(StringUtil.removeHyphen(userFromDB.getMobiNum()));
        String pwd = user.getPswd();

        if (passwordEncoder.matches(pwd, userFromDB.getPswd())) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setData(userFromDB);
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(MessageResolver.getMessage("U00130"));
        }
        return ec;
    }
}
