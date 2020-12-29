package top.veritasal.multithreading.heimademo.demoanonymous;

public class AnonymousThread {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("aaaaaa");
                }
            }
        }.start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("bbbb");
        }
    }
}
