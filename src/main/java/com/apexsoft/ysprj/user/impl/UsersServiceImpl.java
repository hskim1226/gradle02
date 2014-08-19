package com.apexsoft.ysprj.user.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.handler.RowHandler;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.code.AuthorityType;
import com.apexsoft.ysprj.user.domain.Authorities;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.service.UsersService;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private static String NAME_SPACE="com.apexsoft.ysprj.user.Mapper.";

    @Autowired
    private CommonDAO commonDAO;

	public void registerUser(Users users){
		users.setEnabled(true);
        commonDAO.insert(NAME_SPACE+"insertUser", users);

		Authorities authVO = new Authorities();
		authVO.setUsername(users.getUsername());
		authVO.setAuthority(AuthorityType.ROLE_USER.getValue());
        commonDAO.insert(NAME_SPACE+"insertAuthority", authVO);
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
		return commonDAO.queryForObject(NAME_SPACE+"selectByPk",userName, Users.class);
	}

    @Override
    public Users retrieveUserDetail(String userName) {
        return commonDAO.queryForObject(NAME_SPACE + "selectAllByPk", userName, Users.class);
    }

    @Override
    public String retrieveUsername(Users users) {
        return commonDAO.queryForObject(NAME_SPACE + "selectUsername", users, String.class);
    }

    @Override
    public Integer resetPassword(Users users) {
        StringKeyGenerator generator = KeyGenerators.string();
        String key = generator.generateKey();
        users.setPassword(key);
        return commonDAO.update(NAME_SPACE + "update", users);
    }

    @Override
    public Integer changePassword(Users users) {
        return commonDAO.update(NAME_SPACE + "update", users);
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
