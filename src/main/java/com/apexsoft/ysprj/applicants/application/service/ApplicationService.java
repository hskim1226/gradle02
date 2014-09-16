package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    ExecutionContext createApplication(Application application);
    ExecutionContext createEntireApplication(EntireApplication entireApplication);

    EntireApplication retrieveEntireApplication(int applNo);
    <T> T retrieveInfoByApplNo(int applNo, String mapperNameSqlId, Class<T> clazz);
    <T> T retrieveInfoByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz);
    <T> List<T> retrieveInfoListByApplNo(int applNo, String mapperName, Class<T> clazz);
    <T> List<T> retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz);

    ExecutionContext updateEntireApplication(EntireApplication entireApplication);

    ExecutionContext confirmEntireApplication(EntireApplication entireApplication);

    int deleteListByApplNo(int applNo, String MapperName);
}
