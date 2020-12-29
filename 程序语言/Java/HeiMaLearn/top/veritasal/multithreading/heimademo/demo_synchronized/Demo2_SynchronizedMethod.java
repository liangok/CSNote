package top.veritasal.multithreading.heimademo.demo_synchronized;


public class Demo2_SynchronizedMethod {
    public static void main(String[] args) {
        final Printer2 p = new Printer2();
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

class Printer2{
    //非静态的同步方法的锁对象是this
    //静态的是 类名.class（类对象）
    public synchronized void Print1(){          //同步方法只需要加一个关键字
        System.out.print("奥");
        System.out.print("凯");
        System.out.print("集");
        System.out.print("团");
        System.out.print("\r\n");
    }

    public void Print2(){
        synchronized(this){
            System.out.print("领");
            System.out.print("航");
            System.out.print("航");
            System.out.print("空");
            System.out.print("\r\n");
        }
    }
}