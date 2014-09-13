package com.apexsoft.ysprj.applicants.application.service;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.applicants.application.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2014-08-12.
 */
public interface ApplicationService {

    ExecutionContext createEntireApplication(EntireApplication entireApplication);
    <T> int insertItem(T item, String MapperName);
    <T> int insertList(List<T> list, String MapperName);

    EntireApplication retrieveEntireApplication(int applNo);
    <T> T retrieveInfoByApplNo(int applNo, String mapperNameSqlId, Class<T> clazz);
    <T> T retrieveInfoByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz);
    <T> List<T> retrieveInfoListByApplNo(int applNo, String mapperName, Class<T> clazz);
    <T> List<T> retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz);

    ExecutionContext updateEntireApplication(EntireApplication entireApplication);
    <T> int updateItem(T item, String MapperName);
    <T> int updateList(List<T> list, String MapperName);

    int deleteListByApplNo(int applNo, String MapperName);
}
