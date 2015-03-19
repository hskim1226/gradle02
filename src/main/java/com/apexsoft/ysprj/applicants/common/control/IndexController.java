package com.apexsoft.ysprj.applicants.common.control;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by hanmomhanda on 15. 3. 19.
 */
@Controller
public class IndexController {

    @RequestMapping(value="/index", method= RequestMethod.POST)
    public String index(@RequestParam(value="lang") String lang, HttpServletRequest request, ModelAndView mv) {
        WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(lang));
        return "forward:/index.jsp";
    }

}
