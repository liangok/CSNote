package top.veritasal.multithreading.heimademo.demorunable;

public class Demo_Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("My app");
        }
    }

}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("aaaaaaaaaa");
        }
    }
}
