package com.apexsoft.ysprj.user.service.impl;

import com.apexsoft.ysprj.user.service.AuthoritiesVO;
import com.apexsoft.ysprj.user.service.UsersVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("usersDAO")
public class UsersDAO extends SqlSessionDaoSupport{


	
	private static String NAME_SPACE="users.";

	public void insert(UsersVO usersVO){
        getSqlSession().insert(NAME_SPACE+"insert", usersVO);
	}

	public UsersVO selectByPk(String userName) {
        return getSqlSession().selectOne(NAME_SPACE+"selectByPk", userName);
	}
	
	public void insert(AuthoritiesVO authoritiesVO){
        getSqlSession().insert(NAME_SPACE+"insertAuthority", authoritiesVO);
	}

	public void delete(AuthoritiesVO authoritiesVO){
        getSqlSession().insert(NAME_SPACE+"deleteAuthority", authoritiesVO);
	}

//	@SuppressWarnings("unchecked")
//	public List<UsersVO> getUsersList(ComDefaultVO searchVO) {
//		return getSqlSession().selectList(NAME_SPACE+"selectUserList", searchVO);
//	}
//
//    public int getTotalCount(ComDefaultVO searchVO) {
//        return (Integer)getSqlSession().selectOne(NAME_SPACE+"selectTotalCount", searchVO);
//
//    }

    public int updateUsersGrade(String username, String grade) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("username", username);
        param.put("grade", grade);
        return getSqlSession().update(NAME_SPACE+"updateUsersGrade", param);
    }

    public void updateUsers(UsersVO usersVO) {
        getSqlSession().update(NAME_SPACE+"updateUsers", usersVO);
    }

}
