package top.veritasal.multithreading.heimademo.demoexecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo_Executors {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new MyRunnable());
        pool.submit(new MyRunnable());
        pool.shutdown();        //关闭线程池
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "...aaa");
    }
}