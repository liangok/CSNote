package top.veritasal.multithreading.heimademo.demothread;

public class Demo_Thread {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("bbbbbbbbbb");
        }
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("aaaaaaaaaaaaaaaa");
        }
    }
}