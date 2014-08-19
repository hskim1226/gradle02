package com.apexsoft.ysprj.user.web;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.ysprj.user.domain.Users;
import com.apexsoft.ysprj.user.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by Administrator on 2014-08-08.
 */
@Controller
@RequestMapping(value = "/user")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search/id", method = RequestMethod.GET)
    public String displaySearchForm() {
        return "user/search";
    }

    @RequestMapping(value = "/search/id", method = RequestMethod.POST)
    @ResponseBody
    public ExecutionContext searchId(@Valid Users users, BindingResult bindingResult) {
        if( bindingResult.hasErrors() ) {
            return new ExecutionContext( ExecutionContext.FAIL );
        }
        return searchService.searchId(users);
    }

    @RequestMapping(value = "/search/pw", method = RequestMethod.GET)
    public String resetPassword() {
        return "user/searchpw";
    }

}
