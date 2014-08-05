package com.apexsoft.ysprj.notice.service;

import java.util.List;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
public interface NoticeService {
    NoticeVO retrieveNotice(String id);
    List<NoticeVO> listNotice();
}
