package com.apexsoft.ysprj.template.web;

import com.apexsoft.framework.common.vo.ExecutionContext;
import com.apexsoft.framework.persistence.file.FilePersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.web.file.FileHandler;
import com.apexsoft.framework.web.file.callback.UploadEventCallbackHandler;
import com.apexsoft.framework.web.file.exception.UploadException;
import com.apexsoft.ysprj.template.service.ExceptionService;
import com.apexsoft.ysprj.template.service.TempFileService;
import com.apexsoft.ysprj.template.service.TempFileVO;
import com.apexsoft.ysprj.template.web.form.FileMetaForm;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 23.
 * Time: 오후 9:39
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/template")
public class ExceptionController {

    @Autowired
    private ExceptionService exceptionService;

    @RequestMapping(value="/exception", method= RequestMethod.GET)
    public String displayExceptionForm(){
        return "template/exception";
    }

    @RequestMapping(value="/exception", method=RequestMethod.POST)
    public String makeException(){
        exceptionService.makeException();
        return "";
    }

    @RequestMapping(value="/exception/ajax", method=RequestMethod.GET)
    public ExecutionContext makeAjaxException(){
        exceptionService.makeException();
        return new ExecutionContext();
    }
}
