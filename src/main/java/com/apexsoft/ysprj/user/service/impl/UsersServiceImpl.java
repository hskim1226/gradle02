package com.apexsoft.ysprj.user.service.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.handler.RowHandler;
import com.apexsoft.ysprj.code.AuthorityType;
import com.apexsoft.ysprj.user.service.AuthoritiesVO;
import com.apexsoft.ysprj.user.service.UsersService;
import com.apexsoft.ysprj.user.service.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

    private static String NAME_SPACE="users.";

    @Autowired
    private CommonDAO commonDAO;

	@Resource(name="usersDAO")
	private UsersDAO usersDAO;


	public void registerUser(UsersVO usersVO){
		usersVO.setEnabled(true);
        commonDAO.insert(NAME_SPACE+"insert", usersVO);

		AuthoritiesVO authVO = new AuthoritiesVO();
		authVO.setUsername(usersVO.getUsername());
		authVO.setAuthority(AuthorityType.ROLE_USER.getValue());
        commonDAO.insert(NAME_SPACE+"insertAuthority", authVO);
	}

	@Override
	public UsersVO retrieveUser(String userName) {
		return commonDAO.queryForObject(NAME_SPACE+"selectByPk",userName, UsersVO.class);
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
//    public Map<String, Object> getUserPaginatedList(ComDefaultVO searchVO) {
//        Map<String, Object> rtnMap = new HashMap<String, Object>();
//
//        List<UsersVO> users = usersDAO.getUsersList(searchVO);
//        int totalCount = usersDAO.getTotalCount(searchVO);
//
//        rtnMap.put("list", users);
//        rtnMap.put("totalCount", totalCount);
//
//        return rtnMap;
//    }

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
//
//    @Override
//    public void modifyUsers(UsersVO usersVO) {
//        usersDAO.updateUsers(usersVO);
//    }
}
