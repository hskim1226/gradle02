package com.apexsoft.framework.print;

import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by hanmomhanda on 15. 12. 17.
 */
public class PDFPrinter {

    private static boolean jobRunning = true;

    private static class JobCompleteMonitor extends PrintJobAdapter {
        @Override
        public void printJobCompleted(PrintJobEvent pje) {
            System.out.println("Job Completed");
            jobRunning = false;
        }
    }

    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("/home/hanmomhanda/gitRepo/ysproject/src/main/java/com/apexsoft/framework/print/data.pdf");
//        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.PDF;
        DocFlavor docFlavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(Sides.ONE_SIDED);


        PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("print service : " + printService.getName());



//        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(docFlavor, aset);
//        if (printServices.length == 0){
//            throw new IllegalStateException("No Printer Found");
//        }
        PrinterJob job = PrinterJob.getPrinterJob();
//        job.setPrintService(printServices[0]);
        job.setPrintService(printService);

        File pdfFile = new File("/home/hanmomhanda/gitRepo/ysproject/src/main/java/com/apexsoft/framework/print/data.pdf");
        RandomAccessRead raRead = new RandomAccessBufferedFileInputStream(pdfFile);
        PDFParser pdfParser = new PDFParser(raRead);
        pdfParser.parse();
        job.setPageable(new PDFPageable(pdfParser.getPDDocument()));
        job.print();

//        DocPrintJob docPrintJob = printService.createPrintJob();
//        docPrintJob.addPrintJobListener(new JobCompleteMonitor());
//        Doc doc = new SimpleDoc(is, docFlavor, null);
//        docPrintJob.print(doc, null);
//        while(jobRunning){
//            Thread.sleep(1000);
//        }
//        System.out.println("Terminated");
        is.close();
    }
}
