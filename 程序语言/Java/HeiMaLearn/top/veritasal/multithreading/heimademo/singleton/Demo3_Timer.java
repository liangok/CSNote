package top.veritasal.multithreading.heimademo.singleton;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Demo3_Timer {
    public static void main(String[] args) throws InterruptedException {
        Timer t = new Timer();
        t.schedule(new MyTimerTask(), new Date(120,3, 19, 14, 19, 30), 3000);
        while (true){
            Thread.sleep(1000);
            System.out.println(new Date());
        }
    }
}

class MyTimerTask extends TimerTask{
    @Override
    public void run() {
        System.out.println("起床背英语单词");
    }
}