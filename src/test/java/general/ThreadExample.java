package general;

import org.junit.Test;

import java.io.*;

/**
 * Created by hanmomhanda on 15. 5. 12.
 */
public class ThreadExample {

    @Test
    public void performThread() {
        System.out.println(Thread.currentThread().getName());
        long startTime = System.nanoTime();
        for (int i = 0; i < 2000; i++ ) {
            new Thread(String.valueOf(i)) {
                public void run() {
                    new String("Thread : " + getName());
                }
            }.start();
        }
        long endTime = System.nanoTime();
        System.out.println("Elapsed Time : " + (endTime - startTime));
    }

    @Test
    public void singleThread() throws Exception {
        int j = 100;

        long startTime = System.nanoTime();
        String contents = makeContents("/home/hanmomhanda/DocumentController.java");
        while(j-- > 0) {
            writeToFile(contents, "/home/hanmomhanda/ThreadTest" + j + ".java");
        }
        long endTime = System.nanoTime();

        System.out.println("SingleThread : " + (endTime - startTime));
    }

    @Test
    public void multiThread() throws Exception {
        int j = 100;

        long startTime = System.nanoTime();
        String contents = makeContents("/home/hanmomhanda/DocumentController.java");
        while(j-- > 0) {
            new ThreadFileWriter(j, contents).run();
        }
        long endTime = System.nanoTime();

        System.out.println("MultiThread  : " + (endTime - startTime));
    }

    class ThreadFileWriter implements Runnable {

        private int threadIndex;
        private String contents;

        public ThreadFileWriter(int threadIndex, String contents) {
            this.threadIndex = threadIndex;
            this.contents = contents;
        }

        public void run() {
            writeToFile(contents, "/home/hanmomhanda/ThreadTest" + threadIndex + ".java");
        }
    }

    private String readFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private long writeToFile(String contents, String filePath) {
        long length = -1;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contents);
            length = file.length();
            bw.close();
            fw.close();
            return length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    private String makeContents(String filePath) {
        int i = 1000;
        StringBuilder sb = new StringBuilder();
        while(i-- > 0) {
            sb.append(readFromFile(filePath));
        }
        return sb.toString();
    }

}
