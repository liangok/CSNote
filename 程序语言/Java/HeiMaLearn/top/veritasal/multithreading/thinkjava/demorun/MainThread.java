package top.veritasal.multithreading.thinkjava.demorun;

public class MainThread {
    public static void main(String[] args) {
        //NormalMethod();
        //MethodByThread();
        ByThreadUseFor();
    }

    private static void ByThreadUseFor() {
        for (int i = 0; i < 10; i++) {
            new Thread((new LiftOff())).start();    //新创建了线程
            System.out.println("Waiting for LiftOff");  //main中的线程
        }
    }

    /**
     * 通过Thread启用多线程
     */
    private static void MethodByThread() {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }

    /**
     * 普通方法，没有使用多线程
     */
    private static void NormalMethod() {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
