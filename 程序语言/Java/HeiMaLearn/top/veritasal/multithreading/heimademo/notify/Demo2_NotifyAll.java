package top.veritasal.multithreading.heimademo.notify;

public class Demo2_NotifyAll {
    public static void main(String[] args) {
        final Printer2 p = new Printer2();

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

/**
 * 在那个代码块中用哪个对象锁，就用那个对象调用wait()方法
 * 为什么notify和wait要定义在Object类中
 * 锁对象可以是任何对象，而这些对象都是Object的子类，所以将方法定义在Object中就会让任意对象对其进行调用
 * sleep和wait的区别
 * sleep在同步代码块或同步函数中，不释放锁(抱着锁睡)
 * wait在同步代码块或同步函数中，释放锁
 * sleep方法必须传入时间，时间到了自动起来
 * wait可以传参也可以不穿
 * 如果给wait传入参数，用法与sleep类似，时间到就等待停止
 *
 */
class Printer2{
    private int flag = 1;
    public synchronized void Print1() throws InterruptedException {
        synchronized (this) {
            while (flag != 1){
                this.wait();            //被点穴了，没人唤醒它，它就不起来了,if在哪里睡，在哪里醒来，
                                        // while则要进行一次判断（如果都在等待就没法再醒来了）
            }
            System.out.print("奥");
            System.out.print("凯");
            System.out.print("集");
            System.out.print("团");
            System.out.print("\r\n");
            flag = 2;
            this.notifyAll();          //this.notify()随机唤醒单个等待得线程,this.notifyAll()全部唤醒
        }
    }

    public void Print2() throws InterruptedException {
        synchronized(this){
            while (flag != 2){
                this.wait();
            }
            System.out.print("领");
            System.out.print("航");
            System.out.print("航");
            System.out.print("空");
            System.out.print("\r\n");

            flag = 3;
            this.notifyAll();
        }
    }

    public void Print3() throws InterruptedException {
        synchronized(this){
            while (flag != 3){
                this.wait();
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
            this.notifyAll();
        }
    }
}