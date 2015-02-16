package com.apexsoft.framework.web.view;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zbum
 * Date: 2014. 7. 26.
 * Time: 오후 8:42
 * To change this template use File | Settings | File Templates.
 */
public class FileDownloadView extends AbstractView{

    public FileDownloadView(){
        setContentType("application/download; utf-8");
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File downloadFile = (File)model.get("file");

        response.setContentType(getContentType());
        response.setContentLength((int) downloadFile.length());

        String userAgent = request.getHeader("User-Agent");

        boolean ie = userAgent.indexOf("MSIE") > -1;

        String fileName;
        if(ie){
            fileName = URLEncoder.encode(downloadFile.getName(), "utf-8");
        } else {
            fileName = new String(downloadFile.getName().getBytes("utf-8"));
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(downloadFile);
            FileCopyUtils.copy(fis, out);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){}
            }
        }

        out.flush();
    }
}
