package top.veritasal.multithreading.heimademo.demo_synchronized;

public class Demo4_Ticket {
    public static void main(String[] args) {
        MyTicket mt = new MyTicket();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
        new Thread(mt).start();
    }
}

class MyTicket implements Runnable{
    private int ticket = 100;

    @Override
    public void run() {
        while (true){
            synchronized (this) {
                if (ticket == 0) {
                    break;
                }
                //睡眠是模拟在中间有n多行代码的情况,这时会出现无限循环，因为在执行if后若不满足会睡觉，下一个执行，可以通过加一个锁解决
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ".....这是第" + ticket-- + "号票");
            }
        }
    }
}
