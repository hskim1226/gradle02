package com.apexsoft.ysprj.applicants.test;

import com.apexsoft.ysprj.unused.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by hanmomhanda on 14. 8. 20.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value="/qstestForm", method = RequestMethod.GET)
    public String showQueryString(HttpServletRequest request) {
        return "test/qstest";
    }

    @RequestMapping(value="/qstest")
    @ResponseBody
    public String testQueryString(HttpServletRequest request) {
        String r = request.getQueryString();
        return r;
    }

    @RequestMapping(value="/localeMessage/{locale}")
    public String localeMessage(HttpSession session, @PathVariable("locale") String locale) {
        session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(locale));
        return "test/localeMessage";
    }

    @RequestMapping(value="/fileuploadform")
    public String fileuploadForm() {
        return "application/futest";
    }

    @RequestMapping(value="/fileupload")
    @ResponseBody
    public String fileupload() {

        return "abc";
    }

    @RequestMapping(value="/tabsample")
    public String tabSample() {
        return "test/tabsample";
    }

    @RequestMapping(value="/calendar")
    public String calendar() {
        return "test/calendar";
    }

    @RequestMapping(value="/formTest")
    public String formTest() {
        return "test/formTest";
    }

    @RequestMapping(value = "/showAll")
    public String showEntireApplication() {
        return "test/showEntireApplication";
    }

    @RequestMapping(value = "/complexResultMapByOneQuery")
    public String showComplexResultMapByOneQuery(Model model) {

        model.addAttribute("application", testService.retrieveApplication());

        model.addAttribute("joinedApplication", testService.retrieveJoinedApplication());

        model.addAttribute("entireApplication", testService.retrieveEntireApplicationByOneQuery("a00001"));

        return "test/complexResultMap";
    }

    @RequestMapping(value = "/complexResultMapByNestedQuery")
    public String showComplexResultMapByNestedQuery(Model model) {

        model.addAttribute("application", testService.retrieveApplication());

        model.addAttribute("joinedApplication", testService.retrieveJoinedApplication());

        model.addAttribute("entireApplication", testService.retrieveEntireApplicationByNestedQuery("a00001"));

        return "test/complexResultMap";
    }

    @RequestMapping(value = "/checkNullValue")
    public String checkNullValue(@RequestParam String p1, @ModelAttribute String p2) {

        System.out.println("["+p1+"]");
        System.out.println("["+p2+"]");

        return "test/checkNullValue";
    }
}
