package com.apexsoft.ysprj.user.service.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.handler.RowHandler;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.code.AuthorityType;
import com.apexsoft.ysprj.user.service.AuthoritiesVO;
import com.apexsoft.ysprj.user.service.UsersService;
import com.apexsoft.ysprj.user.service.UsersVO;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

    private static String NAME_SPACE="com.apexsoft.ysprj.user.Mapper.";

    @Autowired
    private CommonDAO commonDAO;

	@Resource(name="usersDAO")
	private UsersDAO usersDAO;


	public void registerUser(UsersVO usersVO){
		usersVO.setEnabled(true);
        commonDAO.insert(NAME_SPACE+"insertUser", usersVO);

		AuthoritiesVO authVO = new AuthoritiesVO();
		authVO.setUsername(usersVO.getUsername());
		authVO.setAuthority(AuthorityType.ROLE_USER.getValue());
        commonDAO.insert(NAME_SPACE+"insertAuthority", authVO);
	}

    @Override
    public PageInfo<UsersVO> getUserPaginatedList(UserSearchForm userSearchForm) {
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
	public UsersVO retrieveUser(String userName) {
		return commonDAO.queryForObject(NAME_SPACE+"selectByPk",userName, UsersVO.class);
	}

    @Override
    public UsersVO retrieveUserDetail(String userName) {
        return commonDAO.queryForObject(NAME_SPACE + "selectAllByPk", userName, UsersVO.class);
    }

    @Override
    public String retrieveUsername(UsersVO usersVO) {
        return commonDAO.queryForObject(NAME_SPACE + "selectUsername", usersVO, String.class);
    }

    @Override
    public Integer resetPassword(UsersVO usersVO) {
        StringKeyGenerator generator = KeyGenerators.string();
        String key = generator.generateKey();
        usersVO.setPassword(key);
        return commonDAO.update(NAME_SPACE + "update", usersVO);
    }

    @Override
    public Integer changePassword(UsersVO usersVO) {
        return commonDAO.update(NAME_SPACE + "update", usersVO);
    }

    @Override
    public List<SimpleGrantedAuthority> retrieveAuthorities(String userName) {
        return commonDAO.queryWithResultHandler(NAME_SPACE+"selectAuthorities", userName, new RowHandler<AuthoritiesVO, SimpleGrantedAuthority>() {
            @Override
            public SimpleGrantedAuthority handleRow(AuthoritiesVO authoritiesVO) {
                return new SimpleGrantedAuthority(authoritiesVO.getAuthority());
            }
        });
    }



//    @Override
//    public void modifyUsersGrade(String[] usernames, String[] grades) {
//        if ( usernames == null || grades == null ){
//            return;
//        }
//        for (int inx=0 ; inx < usernames.length ; inx++ ){
//            UsersVO usersVO = usersDAO.selectByPk(usernames[inx]);
//            if ( Integer.parseInt(grades[inx])==usersVO.getGrade()) {
//                continue;
//            }
//            usersDAO.updateUsersGrade(usernames[inx], grades[inx]);
//
//            if ( "8".equals(grades[inx]) ){
//                usersDAO.insert(new AuthoritiesVO().setUsername(usernames[inx]).setAuthority(ROLE_ADMIN));
//            } else {
//                usersDAO.delete(new AuthoritiesVO().setUsername(usernames[inx]).setAuthority(ROLE_ADMIN));
//            }
//
//        }
//    }

    @Override
    public Integer modifyUsers(UsersVO usersVO) {
        return commonDAO.update(NAME_SPACE + "updateUser", usersVO);
    }
}
