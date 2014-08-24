package com.apexsoft.ysprj.qna.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 8. 24.
 * Time: 오후 5:46
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/qna")
public class QnaController {

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String displayQnaList(){

        return "qna/list";
    }
}
