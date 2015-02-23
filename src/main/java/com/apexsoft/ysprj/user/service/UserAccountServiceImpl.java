package com.apexsoft.ysprj.user.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.exception.YSBizException;
import com.apexsoft.framework.message.MessageResolver;
import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.handler.RowHandler;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.applicants.user.domain.Authorities;
import com.apexsoft.ysprj.code.AuthorityType;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private static String NAME_SPACE="com.apexsoft.ysprj.user.Mapper.";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageResolver messageResolver;

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public ExecutionContext registerUserAndAuthority(Users users) {
        ExecutionContext ec = new ExecutionContext();
        int rUserInsert = 0, rAuthInsert = 0;

        users.setEnabled( true );
        users.setPswd( passwordEncoder.encode( users.getPswd() ) );
        rUserInsert = commonDAO.insert( NAME_SPACE + "insertUser", users );

        Authorities authVO = new Authorities();
        authVO.setUsername(users.getUserId());
        authVO.setAuthority(AuthorityType.ROLE_USER.getValue());
        rAuthInsert = commonDAO.insert(NAME_SPACE+"insertAuthority", authVO);

        if ( rUserInsert == 1 && rAuthInsert == 1) {
            ec.setResult(ExecutionContext.SUCCESS);
            ec.setMessage(messageResolver.getMessage("U103"));
        } else {
            ec.setResult(ExecutionContext.FAIL);
            ec.setMessage(messageResolver.getMessage("U104"));
            String errCode = null;
            if ( rUserInsert != 1 ) errCode = "ERR1001";
            if ( rAuthInsert != 1 ) errCode = "ERR1002";
            ec.setErrCode(errCode);
            throw new YSBizException(ec);
        }
        return ec;
    }

    public ExecutionContext registerUser(Users users){
        try {
            registerUserAndAuthority(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ExecutionContext();
	}

    @Override
    public PageInfo<Users> getUserPaginatedList(UserSearchForm userSearchForm) {
        return commonDAO.queryForPagenatedList(new PageStatement(){
            /**
             * @return the totalCountStatementId
             */
            public String getTotalCountStatementId() {
                return NAME_SPACE+"selectTotalCount";
            }

            /**
             * @return the dataStatementId
             */
            public String getDataStatementId() {
                return NAME_SPACE+"selectUserList";
            }
        }, new UserSearchForm(), userSearchForm.getPageNum(), userSearchForm.getPageRows() );

    }

    @Override
	public Users retrieveUser(String userName) {
        Users users = commonDAO.queryForObject(NAME_SPACE+"selectByPk",userName, Users.class);

		return users;
	}

    @Override
    public ExecutionContext retrieveUserIds(Users users, int showLength) {
        List<String> list = commonDAO.queryForList(NAME_SPACE + "selectUsername", users, String.class);
        ExecutionContext context = new ExecutionContext();
        if( list.size() > 0 ) {
            String[] userIdArray = new String[list.size()];
            for( int i = 0; i < list.size(); i++ ) {
                String userId = list.get(i);
                int show = showLength == -1 ? userId.length() : showLength;
                userId = userId.substring(0, showLength) + StringUtils.repeat("*", userId.length() - showLength);
                userIdArray[i] = userId;
            }
            context.setData(userIdArray);
            context.setMessage( "다음의 ID가 검색되었습니다." );
        } else {
            context.setResult( ExecutionContext.FAIL );
            context.setMessage( "입력하신 정보와 일치하는 ID가 없습니다. ");
        }
        return context;
    }

    @Override
    public Integer resetPassword(Users users) {
        StringKeyGenerator generator = KeyGenerators.string();
        String key = generator.generateKey();
        users.setPswd(key);
        return changePassword(users);
    }

    @Override
    public Integer changePassword(Users users) {
        users.setPswd( passwordEncoder.encode( users.getPswd() ) );
        return commonDAO.update(NAME_SPACE + "changePasswd", users);
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
    public ExecutionContext isUserIdAvailable(Users users) {
        ExecutionContext context = new ExecutionContext();
        if( retrieveUser( users.getUserId() ) != null ) {
            context.setResult( ExecutionContext.FAIL );
            context.setMessage( "이미 존재하는 ID 입니다." );
        }
        context.setMessage( "유효한 ID 입니다." );
        return context;
    }

//    @Override
//    public void modifyUsersGrade(String[] usernames, String[] grades) {
//        if ( usernames == null || grades == null ){
//            return;
//        }
//        for (int inx=0 ; inx < usernames.length ; inx++ ){
//            Users usersVO = usersDAO.selectByPk(usernames[inx]);
//            if ( Integer.parseInt(grades[inx])==usersVO.getGrade()) {
//                continue;
//            }
//            usersDAO.updateUsersGrade(usernames[inx], grades[inx]);
//
//            if ( "8".equals(grades[inx]) ){
//                usersDAO.insert(new Authorities().setUsername(usernames[inx]).setAuthority(ROLE_ADMIN));
//            } else {
//                usersDAO.delete(new Authorities().setUsername(usernames[inx]).setAuthority(ROLE_ADMIN));
//            }
//
//        }
//    }

    @Override
    public Integer modifyUsers(Users users) {
        return commonDAO.update(NAME_SPACE + "updateUser", users);
    }
}
