package top.veritasal.multithreading.heimademo.notify;

public class Demo1_Notify {
    /**
     * 等待唤醒机制
     * @param args
     */
    public static void main(String[] args) {
        Printer p = new Printer();
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
    }
}

class Printer{
    private int flag = 1;
    public synchronized void Print1() throws InterruptedException {
        synchronized (this) {
            if(flag != 1){
                this.wait();            //被点穴了，没人唤醒它，它就不起来了
            }
            System.out.print("奥");
            System.out.print("凯");
            System.out.print("集");
            System.out.print("团");
            System.out.print("\r\n");
            flag = 2;
            this.notify();          //随机唤醒单个等待得线程
        }
    }

    public void Print2() throws InterruptedException {
        synchronized(this){
            if(flag != 2){
                this.wait();
            }
            System.out.print("领");
            System.out.print("航");
            System.out.print("航");
            System.out.print("空");
            System.out.print("\r\n");

            flag = 1;
            this.notify();
        }
    }
}