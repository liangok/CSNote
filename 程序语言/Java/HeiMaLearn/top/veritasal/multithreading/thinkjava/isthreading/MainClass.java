package top.veritasal.multithreading.thinkjava.isthreading;

public class MainClass {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Demo();
        }

        /*for (int i = 0; i < 100000; i++) {
            System.out.println("I am main threading");
        }*/
    }
}

class Demo{

    @Override
    public void finalize(){
        System.out.println("垃圾被回收了");
    }
}
