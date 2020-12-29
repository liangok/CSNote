package top.veritasal.multithreading.heimademo.demoanonymous;

public class Getname {
    public static void main(String[] args) {
        new Thread("小明"){
            @Override
            public void run() {
                this.setName("小梁");
                System.out.println(this.getName() + "......我是小明");
            }
        }.start();

        new Thread("小红"){
            @Override
            public void run() {
                System.out.println(getName() + ".....我是小红");
            }
        }.start();
    }
}
