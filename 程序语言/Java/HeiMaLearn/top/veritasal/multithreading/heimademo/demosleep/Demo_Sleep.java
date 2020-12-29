package top.veritasal.multithreading.heimademo.demosleep;

public class Demo_Sleep {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println("领航航空公司");
                System.out.println("火箭发射！");
                for (int i = 10; i >=0; i--) {
                    try {
                        sleep(1000);
                        System.out.println("倒计时" + i + "秒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try{
                    sleep(100);
                    System.out.println("点火");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                try {
                    sleep(1000);
                    System.out.println("火箭已升空！");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                try{
                    sleep(1000);
                    System.out.println("遥感正常，飞船进入轨道");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                try {
                    sleep(1000);
                    System.out.println("开启低耗能模式，目标：火星");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                for (int i = 2; i >= 1; i--) {
                    try {
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("还有" + i + "年到达");
                }

                try {
                    sleep(1000);
                    System.out.println("飞船已到达Mark！准备降落");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
