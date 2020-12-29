package top.veritasal.multithreading.heimademo.demo_synchronized;

public class Demo3_Ticket {
    /**
     * 铁路售票，一共100张，通过四个窗口买完
     * @param args
     */
    public static void main(String[] args) {
        new Ticket().start();
        new Ticket().start();
        new Ticket().start();
        new Ticket().start();
    }
}

class Ticket extends Thread{
    private static int ticket = 100;

    @Override
    public void run() {
        while (true){
            synchronized (Ticket.class) {
                if (ticket == 0) {
                    break;
                }
                //睡眠是模拟在中间有n多行代码的情况,这时会出现无限循环，因为在执行if后若不满足会睡觉，下一个执行，可以通过加一个锁解决
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + ".....这是第" + ticket-- + "号票");
            }
        }
    }
}