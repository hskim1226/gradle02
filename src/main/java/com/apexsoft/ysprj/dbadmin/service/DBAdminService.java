package com.apexsoft.ysprj.dbadmin.service;

import com.apexsoft.ysprj.dbadmin.domain.Adms;

import java.util.List;

/**
 * Created by cosb071 on 15. 3. 6.
 */
public interface DBAdminService {

    List<Adms> retrieveADMSList( );

    void registerADMS( Adms adms );

}
