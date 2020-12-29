package top.veritasal.multithreading.heimademo.demo_synchronized;

public class Demo_Synchronized {
    public static void main(String[] args) {

        final Printer p = new Printer();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    p.Print1();

                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    p.Print2();
                }
            }
        }.start();
    }
}

class Printer{
    Lock l = new Lock();        //锁可以是任意对象
    public void Print1(){
        synchronized (l){
            System.out.print("奥");
            System.out.print("凯");
            System.out.print("集");
            System.out.print("团");
            System.out.print("\r\n");
        }
    }

    public void Print2(){
        synchronized (l) {
            System.out.print("领");
            System.out.print("航");
            System.out.print("航");
            System.out.print("空");
            System.out.print("\r\n");
        }
    }
}

class Lock{}