package com.apexsoft.ysprj.template.web;

import com.apexsoft.ysprj.template.service.TempFileService;
import com.apexsoft.ysprj.template.service.TempFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
public class TemplateFileDownloadController {

    @Autowired
    private TempFileService tempFileService;

    @RequestMapping(value="/download", method= RequestMethod.GET)
    public String displayDownloadList(HttpServletRequest request){
        List<TempFileVO> files = tempFileService.getFiles();
        request.setAttribute("files", files);
        return "template/download";
    }

    @RequestMapping(value="/download/{fileSeq}", method=RequestMethod.GET)
    public ModelAndView doDownload(@PathVariable("fileSeq") int fileSeq){
        return new ModelAndView("downloadView", "file", tempFileService.getFile(fileSeq));
    }

}
