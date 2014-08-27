package com.apexsoft.ysprj.qna.service.impl;

import com.apexsoft.framework.persistence.dao.CommonDAO;
import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.framework.persistence.dao.page.PageStatement;
import com.apexsoft.ysprj.qna.service.QnaService;
import com.apexsoft.ysprj.qna.service.QnaVO;
import com.apexsoft.ysprj.qna.web.form.QnaSearchForm;
import com.apexsoft.ysprj.user.web.form.UserSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QnaServiceImpl implements QnaService{

    private static String NAME_SPACE = "qna.";

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public PageInfo<QnaVO> getQnaPaginatedList(QnaSearchForm qnaSearchForm) {
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
                return NAME_SPACE+"selectQnaList";
            }
        }, new UserSearchForm(), qnaSearchForm.getPageNum(), qnaSearchForm.getPageRows() );
    }
}
