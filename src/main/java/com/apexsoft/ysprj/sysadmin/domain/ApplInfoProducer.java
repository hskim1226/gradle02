package com.apexsoft.ysprj.sysadmin.domain;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ApplInfoProducer implements Runnable {

    private final BlockingQueue<BackUpApplDoc> applInfoQue;
    private List<BackUpApplDoc> backUpApplDocList;

    public ApplInfoProducer(BlockingQueue<BackUpApplDoc> applInfoQue, List<BackUpApplDoc> backUpApplDocList) {
        this.applInfoQue = applInfoQue;
        this.backUpApplDocList = backUpApplDocList;
    }

    @Override
    public void run() {
        for (BackUpApplDoc backUpApplDoc : backUpApplDocList) {
            try {
                applInfoQue.put(backUpApplDoc);
            } catch ( InterruptedException e ) {
                String tId = "<Thread-" + Thread.currentThread().getId() + "> ";
                System.err.println(tId +"^^ ERROR in " +
                        Thread.currentThread().getStackTrace()[1].getClassName() + "." +
                        Thread.currentThread().getStackTrace()[1].getMethodName());
                System.err.print(tId + "applDocInfo : " + backUpApplDoc.getApplNo());
            }
        }

        //  For Test
//            for (int i = 0 ; i < 3 ; i++) {
//                try {
//                    applInfoQue.put(backUpApplDocList.get(i));
//                } catch ( InterruptedException e ) {
//                    e.printStackTrace();
//                }
//            }
    }
}

