package top.veritasal.multithreading.heimademo.demodaemon;

public class Demo_Daemon {

    /**
     * 守护线程
     * 有点像象棋里的车马炮和帅的关系，帅一死，车马炮跟着完蛋
     * @param args
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    System.out.println(getName() + ".....Hell0");
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    System.out.println(getName() + ".....Nice to meet you");
                }
            }
        };

        t2.setDaemon(true);
        t2.start();
        t1.start();
    }
}
