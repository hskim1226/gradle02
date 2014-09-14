package com.apexsoft.ysprj.admin.control;

import com.apexsoft.framework.message.MessageResolver;

import com.apexsoft.ysprj.admin.control.form.ChangeInfoForm;
import com.apexsoft.ysprj.admin.service.CancelService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


/**
 * Created by DhKim on 14. 9. 14.
 */
@Controller
@RequestMapping(value="/admin")
public class CancelController {


    @Autowired
    private CancelService cancelService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;    
    
    @SuppressWarnings("restriction")
	@Resource(name = "messageResolver")
    private MessageResolver messageResolver;

    @RequestMapping(value="/modification/requestChangeInfo")
    public String requestChangeInfo( ChangeInfoForm changeInfoForm, Model model) {
        cancelService.changeApplInof(changeInfoForm);
        return "admin/modification/list";
    }
    

}
