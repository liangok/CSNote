package top.veritasal.multithreading.heimademo.demojoin;

public class Demo_Join {
    /**
     * 加入线程，有点像插队
     * @param args
     */
    public static void main(String[] args) {
        final Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(getName() + ".......aha");
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (i == 2){
                        try {
                            t1.join();  //匿名类在使用局部变量时必须时final的
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(getName() + ".......yoho");
                }
            }
        };

        t1.start();
        t2.start();
    }
}
