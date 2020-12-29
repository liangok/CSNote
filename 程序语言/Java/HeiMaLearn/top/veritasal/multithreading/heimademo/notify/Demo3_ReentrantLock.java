package top.veritasal.multithreading.heimademo.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo3_ReentrantLock {
    public static void main(String[] args) {
        Printer3 p = new Printer3();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        p.Print1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        p.Print2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        p.Print3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}

class Printer3{
    private ReentrantLock r = new ReentrantLock();
    private Condition c1 =r.newCondition();     //监视器
    private Condition c2 =r.newCondition();
    private Condition c3 =r.newCondition();
    private int flag = 1;
    public synchronized void Print1() throws InterruptedException {
        r.lock();       //获取锁
            while (flag != 1){
                c1.await();

            }
            System.out.print("奥");
            System.out.print("凯");
            System.out.print("集");
            System.out.print("团");
            System.out.print("\r\n");
            flag = 2;
            c2.signal();
        r.unlock();     //释放锁
    }

    public void Print2() throws InterruptedException {
        r.lock();
            while (flag != 2){
                c2.await();
            }
            System.out.print("领");
            System.out.print("航");
            System.out.print("航");
            System.out.print("空");
            System.out.print("\r\n");

            flag = 3;
            c3.signal();
        r.unlock();
    }

    public void Print3() throws InterruptedException {
        r.lock();
            while (flag != 3){
                c3.await();
            }
            System.out.print("V");
            System.out.print("e");
            System.out.print("r");
            System.out.print("i");
            System.out.print("t");
            System.out.print("a");
            System.out.print("s");
            System.out.print("a");
            System.out.print("l");
            System.out.print("\r\n");

            flag = 1;
            c1.signal();
        r.unlock();
    }
}