package com.apexsoft.ysprj.admin.web;

import com.apexsoft.framework.persistence.dao.page.PageInfo;
import com.apexsoft.ysprj.qna.service.QnaService;
import com.apexsoft.ysprj.qna.service.QnaVO;
import com.apexsoft.ysprj.qna.web.form.QnaSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 8. 27.
 * Time: 오후 9:08
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin/qna")
public class AdminQnaController {
    @Autowired
    private QnaService qnaService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String displayQnaList(QnaSearchForm searchForm, Model model){
        PageInfo<QnaVO> qnaVOPageInfo = qnaService.getQnaPaginatedList(searchForm);

        model.addAttribute("qna", qnaVOPageInfo.getData());
        model.addAttribute("qnaTotal", qnaVOPageInfo.getTotalRowCount());

        return "admin/qna/list";
    }

    @RequestMapping(value="/detail", method = RequestMethod.GET)
    public String displayQnaDetail(@RequestParam("id") int id, Model model){
        QnaVO qnaVO = qnaService.getQna(id);

        model.addAttribute("qna", qnaVO);

        return "admin/qna/detail";
    }

    @RequestMapping(value="/answer", method = RequestMethod.POST)
    public String saveAnswer(QnaVO qna){
        qnaService.saveAnswer(qna);

        return "redirect:/admin/qna/list";
    }
}
