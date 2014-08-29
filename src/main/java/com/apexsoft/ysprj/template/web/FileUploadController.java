package com.apexsoft.ysprj.template.web;

import com.apexsoft.framework.persistence.file.PersistenceManager;
import com.apexsoft.framework.persistence.file.model.FileInfo;
import com.apexsoft.framework.persistence.file.model.FileItem;
import com.apexsoft.framework.web.file.FileHandler;
import com.apexsoft.framework.web.file.callback.UploadEventCallbackHandler;
import com.apexsoft.framework.web.file.exception.UploadException;
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
public class FileUploadController {

    @Autowired
    private TempFileService fileUploadService;

    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String displayUploadForm(){
        return "template/upload";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String upload(FileHandler fileHandler){

        return fileHandler.handleMultiPartRequest(new UploadEventCallbackHandler<String,FileMetaForm>(){

            @Override
            protected String getDirectory(String fileFieldName, FileMetaForm fileMetaForm, String leafDirectory) {
                return "2";  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            protected String createFileName(String fileFieldName, String originalFileName, FileMetaForm fileMetaForm) {
                return originalFileName;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public String handleEvent(List<FileItem> fileItems, FileMetaForm fileMetaForm, PersistenceManager persistence) {

                FileInfo fileInfo;
                TempFileVO tempFileVO = new TempFileVO();

                for ( FileItem fileItem : fileItems){
                    FileInputStream fis = null;
                    try{
                        // persistence.save()의 첫번째 인자로 baseDir/첫번째인자 라는 폴더 생성
                        //
                        fileInfo = persistence.save("/", fileItem.getOriginalFileName(), fileItem.getOriginalFileName(), fis = new FileInputStream(fileItem.getFile()));
                        tempFileVO.setPath(fileInfo.getDirectory());
                        tempFileVO.setFileName(fileInfo.getFileName());
                    }catch(FileNotFoundException fnfe){
                        throw new UploadException("", fnfe);
                    }finally {
                        try {
                            if (fis!= null) fis.close();
                        } catch (IOException e) {}
                        FileUtils.deleteQuietly(fileItem.getFile());
                    }
                }

                fileUploadService.saveFileMeta(tempFileVO);

                return "redirect:/template/download";
            }
        }, FileMetaForm.class);
    }
}
