package com.apexsoft.ysprj.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hanmomhanda on 14. 8. 6.
 */
@Controller
@RequestMapping(value="/notice")
public class NoticeController {

    @RequestMapping(value="/list")
    public String listNotice() {
        return "notice/list";
    }

    @RequestMapping(value="/2015-1-general")
    public String detailGeneral() {
        return "notice/2015-1-general";
    }
}
