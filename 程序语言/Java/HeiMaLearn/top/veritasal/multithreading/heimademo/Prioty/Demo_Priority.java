package top.veritasal.multithreading.heimademo.Prioty;

public class Demo_Priority {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(getName() + ".....aaaaa");
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(getName() + "...bbbbbbbbbb");
                }
            }
        };

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(1);

        t2.start();
        t1.start();
    }
}
