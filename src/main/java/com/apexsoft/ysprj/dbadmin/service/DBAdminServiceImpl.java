package com.apexsoft.ysprj.dbadmin.service;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.ysprj.dbadmin.domain.Adms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cosb071 on 15. 3. 6.
 */
@Service
public class DBAdminServiceImpl implements DBAdminService {

    private final static String NAME_SPACE = "com.apexsoft.ysprj.dbadmin.sqlmap.";

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public List<Adms> retrieveADMSList( ) {

        List<Adms> admsList = commonDAO.queryForList( NAME_SPACE + "AdmsMapper.selectByExample", Adms.class );

        return admsList;
    }

    @Override
    public void registerADMS( Adms adms ) {

    }
}
