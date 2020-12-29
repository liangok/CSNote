package top.veritasal.multithreading.heimademo.demoanonymous;

public class GetThread {
    public static void main(String[] args) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            System.out.println(Thread.currentThread().getName() + "....猜猜我是谁");
                        }
                    }
                }
        ).start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            System.out.println(Thread.currentThread().getName() + "....你绝对想不到");
                        }
                    }
                }
        ).start();

        Thread.currentThread().setName("我是主线程");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());

        }
    }
}
