package top.veritasal.multithreading.heimademo.singleton;

public class Demo1_Singleton {
    public static void main(String[] args) {
        /*Singleton s1 = Singleton.s;
        Singleton s2 = Singleton.s;

        System.out.println(s1 == s2);*/

        //弊端，会输出false，可以把newSingleton改为私有
        /*Singleton.s = null;
        Singleton s2 = Singleton.s;
        System.out.println(s2 == s1);*/

        Singleton s1 = Singleton.getSingleton();
        Singleton s2 = Singleton.getSingleton();
        System.out.println(s1 == s2);

    }


}

/**
 * 饿汉式
 * 上来就创建对象，不管你用不用

class Singleton{
    //1.私有构造方法,其他类不能创建
    private Singleton(){}
    //2.创建本类对象
    private static Singleton s = new Singleton();
    //3.对外提供公共的访问方法
    public static Singleton getSingleton() {
        return s;
    }
}

 */

/**
 * 懒汉式，单例的延迟加载模式
 * 线程不安全
 */
class Singleton{
    //1.私有构造方法,其他类不能创建
    private Singleton(){}
    //2.声明一个引用
    private static Singleton s;
    //3.对外提供公共的访问方法
    public static Singleton getSingleton() {
        if(s == null){
            //线程1等待，线程2等待。会创建两个Singleton
            s = new Singleton();
        }
        return s;
    }
}


/**
 *1.饿汉式是用空间换取时间，懒汉式是时间换取空间
 *2.懒汉式线程不安全
 *
 */