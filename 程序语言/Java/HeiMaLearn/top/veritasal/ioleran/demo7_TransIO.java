package top.veritasal.ioleran;

import java.io.*;

public class demo7_TransIO {

    public static void main(String[] args) throws IOException {
        //demo1();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("utf-8.txt"),"utf-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk"));

        int c;
        while ((c = br.read()) != -1){
            bw.write(c);
        }

        br.close();
        bw.close();
    }

    private static void demo1() throws IOException {
        FileInputStream fis = new FileInputStream("utf-8.txt");
        FileOutputStream fos = new FileOutputStream("gbk.txt");

        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");

        int c;
        while ((c = isr.read()) != -1){
            osw.write(c);
        }

        isr.close();
        osw.close();
    }
}
