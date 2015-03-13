package com.apexsoft.ysprj.unused;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.common.control.BirtController;
import com.apexsoft.ysprj.applicants.common.domain.BirtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by hanmomhanda on 15. 3. 13.
 */
@Controller
@RequestMapping(value="/application")
public class TestCasNoteController {

    @Autowired
    private BirtController birtController;

    @RequestMapping(value="/testCasNote/{applNo}")
    public ModelAndView testCasNote(@PathVariable("applNo") int applNo,
                                    Principal principal,
                                    ModelAndView mv) {

        Application application = new Application();
        application.setApplNo(applNo);
        BirtRequest birtRequest = new BirtRequest();
        birtRequest.setApplication(application);

        birtController.generateSlipFile(birtRequest, principal, mv);

        return mv;
    }
}
