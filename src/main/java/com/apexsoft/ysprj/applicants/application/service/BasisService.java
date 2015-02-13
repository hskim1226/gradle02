package com.apexsoft.ysprj.applicants.application.service;

        import com.apexsoft.framework.common.vo.ExecutionContext;
        import com.apexsoft.ysprj.applicants.application.domain.*;

        import java.util.List;

/**
 * Created by hanmomhanda on 15. 1. 9.
 *
 * 원서 기본 정보 서비스
 */
public interface BasisService {

    ExecutionContext retrieveSelectionMap(Basis basis);

    ExecutionContext retrieveBasis(int applNo);

    ExecutionContext retrieveBasis(Basis basis);

    ExecutionContext saveBasis(Basis basis);

    <T> ExecutionContext retrieveInfoListByParamObj(Object parameter, String mapperNameSqlId, Class<T> clazz);
}
