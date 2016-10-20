package com.apexsoft.ysprj.applicants.application.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class FindApplicationController {

    /**
     * 수험번호 찾기
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/findApplId", method = RequestMethod.GET)
    public String findApplId() throws JsonProcessingException {
    	return "application/findApplId";
    }

}
