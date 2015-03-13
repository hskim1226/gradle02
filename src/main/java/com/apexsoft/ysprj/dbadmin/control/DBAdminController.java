package com.apexsoft.ysprj.dbadmin.control;

import com.apexsoft.ysprj.applicants.application.domain.Application;
import com.apexsoft.ysprj.applicants.payment.service.PaymentService;
import com.apexsoft.ysprj.dbadmin.domain.Adms;
import com.apexsoft.ysprj.dbadmin.service.DBAdminService;
import org.eclipse.birt.core.format.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by cosb071 on 15. 3. 6.
 */
@Controller
@RequestMapping(value = "/dbadmin")
public class DBAdminController {

    @Autowired
    private DBAdminService dbAdminService;

    @RequestMapping(value="/adms")
    public ModelAndView test1( ModelAndView mv ) {

        List<Adms> admsList = dbAdminService.retrieveADMSList();
        mv.addObject( "admsList", admsList );

        mv.setViewName("dbadmin/adms");

        Date date = new Date();




        return mv;
    }

}
