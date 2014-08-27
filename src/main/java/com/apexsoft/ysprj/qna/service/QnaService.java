package com.apexsoft.ysprj.qna.service;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.qna.web.form.QnaSearchForm;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 8. 24.
 * Time: 오후 6:22
 * To change this template use File | Settings | File Templates.
 */
public interface QnaService {
    PageInfo<QnaVO> getQnaPaginatedList(QnaSearchForm searchForm);

    QnaVO getQna(int id);
}
