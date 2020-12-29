package top.veritasal.multithreading.heimademo.singleton;

import java.io.IOException;

public class Demo2_Runtime {
    public static void main(String[] args) throws IOException {
        //操作对象是一个
        Runtime r = Runtime.getRuntime();
        //r.exec("shutdown -s -t 300");
        r.exec("shutdown -a");
    }
}
