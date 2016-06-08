package com.apexsoft.ysprj.sysadmin.domain;

import com.apexsoft.ysprj.applicants.application.domain.ApplicationDocument;
import com.apexsoft.ysprj.applicants.application.service.DocumentService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by go2zo on 2016. 6. 7.
 */
public class CustomApplInfoProducer extends ApplInfoProducer {

    public final static int IGNORE_PHOTO = 0x0001;
    public final static int IGNORE_APPL = 0x0002;
    public final static int IGNORE_SLIP = 0x0004;
    public final static int IGNORE_DOC = 0x0008;
    public final static int IGNORE_RECOM = 0x0010;

    private int ignoreFlag = IGNORE_PHOTO;

    private DocumentService documentService;

    public CustomApplInfoProducer(BlockingQueue<BackUpApplDoc> applInfoQue, List<BackUpApplDoc> backUpApplDocList) {
        super(applInfoQue, backUpApplDocList);
    }

    public void setIgnoreFlag(int flag) {
        this.ignoreFlag = flag;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public void run() {
        for (BackUpApplDoc backUpApplDoc : getBackUpApplDocList()) {
            try {
                int applNo = backUpApplDoc.getApplNo();

                List<ApplicationDocument> docList = new ArrayList<>();

                // 지원서
                if ((IGNORE_APPL & ignoreFlag) == 0) {
                    docList.addAll(documentService.retrieveApplicationPaperInfo(applNo));
                }

                // 첨부파일
                if ((IGNORE_DOC & ignoreFlag) == 0) {
                    docList.addAll(documentService.retrieveApplicationDocuments(applNo));
                }

                // 추천서
                if ((IGNORE_RECOM & ignoreFlag) == 0) {
                    docList.addAll(documentService.retrieveRecommendationDocuments(applNo));
                }

                // 수험표
                if ((IGNORE_SLIP & ignoreFlag) == 0) {
                }

                // 사진
                if ((IGNORE_PHOTO & ignoreFlag) == 0) {
                    BackUpApplDocExt backUpApplDocExt = new BackUpApplDocExt(backUpApplDoc);
                    String photoUri = documentService.retrievePhotoUri(applNo);
                    backUpApplDocExt.setFileName(photoUri);
                }

                for (ApplicationDocument doc : docList) {
                    BackUpApplDocExt backUpApplDocExt = new BackUpApplDocExt(backUpApplDoc);
                    backUpApplDocExt.setFileName(doc.getFileName());
                    backUpApplDocExt.setTargetFileName(doc.getOrgFileName());
                    getApplInfoQue().put(backUpApplDocExt);
                }

            } catch ( InterruptedException e ) {
                String tId = "<Thread-" + Thread.currentThread().getId() + "> ";
                System.err.println(tId +"^^ ERROR in " +
                        Thread.currentThread().getStackTrace()[1].getClassName() + "." +
                        Thread.currentThread().getStackTrace()[1].getMethodName());
                System.err.print(tId + "applDocInfo : " + backUpApplDoc.getApplNo());
            }
        }
    }
}
