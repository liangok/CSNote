package top.veritasal.multithreading.heimademo.demo_synchronized;

public class Demo5_DeadLock {

    private static String s1 = "筷子左";
    private static String s2 = "筷子右";

    /**
     * 为什么拿不到？
     * 因为S2在thread1里thread0没法拿
     * 所以同步线程不要嵌套
     * @param args
     */
    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                while (true){
                    //拿到s1并没有释放
                    synchronized (s1){
                        System.out.println(getName() + "...获取" + s1 + "等待" + s2);
                        synchronized (s2){
                            System.out.println(getName() + "...拿到" + s2 + "开吃");
                        }
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){
                    //拿到s2并没有释放
                    synchronized (s2){
                        System.out.println(getName() + "...获取" + s2 + "等待" + s1);
                        synchronized (s1){
                            System.out.println(getName() + "...拿到" + s1 + "开吃");
                        }
                    }
                }
            }
        }.start();
    }
}
