package com.apexsoft.ysprj.user.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.user.domain.Campus;
import com.apexsoft.ysprj.user.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 21.
 * Time: 오후 10:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CampusServcieImpl implements CampusService {

    private final static String NAME_SPACE="com.apexsoft.ysprj.user.campusMapper.";

    @Autowired
    CommonDAO commonDao;

    @Override
    public List<Campus> retriveCampusList() {
        return commonDao.queryForList(NAME_SPACE+"selectCampusList", Campus.class);

    }
}
