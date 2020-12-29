package top.veritasal.ioleran;

import java.io.FileWriter;
import java.io.IOException;

public class Demo2_FileWriter {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("yyy.txt", true);    //true的作用：不清空以前的文件
        fw.write("大家好, I am Liang Aokai");

        fw.close();
    }
}
