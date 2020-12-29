package top.veritasal.multithreading.thinkjava.demoexecutor;

import top.veritasal.multithreading.thinkjava.demorun.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutor {
    public static void main(String[] args) {
        //FixedThreadPool();
        //CachedThreadPool();
        //SingleThreadExecutor();
    }

    /**
     * 一次进行一个线程
     * 常用于需要让线程顺序执行，并且在任意时间，只能有一个任务被执行，
     * 而不能有多个线程同时执行的场景。
     */
    private static void SingleThreadExecutor() {
        ExecutorService exec = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
    }

    /**
     *CachedThreadPool适用于执行很多短期异步任务的小程序，或者是负载较轻的服务器。
     * 因为maximumPoolSize=Integer.MAX_VALUE，因此可以不断的创建新线程，这样可能会CPU和内存资源耗尽。
     */
    private static void CachedThreadPool() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
            System.out.println("MainThread");
        }
        exec.shutdown();
    }

    /**
     * FixedThreadPool是线程数量固定的线程池，适用于为了满足资源管理的需求，而需要适当限制当前线程数量的情景，适用于负载比较重的服务器。
     *
     */
    private static void FixedThreadPool() {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
