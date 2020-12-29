package top.veritasal.multithreading.heimademo.demoanonymous;

public class AnonymousRunnable {
    public static void main(String[] args) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("aaaaaaaa");
                }
            }
        }).start();

        for (int i = 0; i < 10000; i++) {
            System.out.println("bbbbbbbbbbbbb");
        }
    }
}
