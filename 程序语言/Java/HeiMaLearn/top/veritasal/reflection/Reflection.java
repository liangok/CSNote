package top.veritasal.reflection;

import top.veritasal.reflection.Demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {
    public static void main(String[] args) throws Exception {

        //获得类名的3种方法
        //1.全类名,多用于配置文件
        Class c1 = Class.forName("top.veritasal.reflection.Demo");
        System.out.println(c1.toString());
        //2.类名.class，多用于参数传递
        Class c2 = Demo.class;
        System.out.println(c2);
        //3.对象.getClass()，多用于对象的获取字节码的方式
        Demo d = new Demo();
        Class c3 = d.getClass();
        System.out.println(c3);
        System.out.println("==============================");
        System.out.println("获取成员变量");
        System.out.println("===============================");

        //1.获取成员变量
        Field[] declaredFields = c2.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        //获取特定成员变量
        Field c2DeclaredField = c2.getDeclaredField("s");
        //由于是私有，所以要暴力反射
        c2DeclaredField.setAccessible(true);
        System.out.println(c2DeclaredField);
        System.out.println();

        //获取某个成员变量的number的值
        c2DeclaredField.get(d);
        c2DeclaredField.set(d, "张三");
        System.out.println(d);

        //获取方法
        System.out.println("==============================");
        System.out.println("获取构造方法");
        System.out.println("===============================");
        //1.获取构造方法
        Constructor constructor = c2.getConstructor(String.class, int.class);
        System.out.println(constructor);
        Object instance = constructor.newInstance("张三", 3);
        System.out.println(instance);

        System.out.println("===============================");
        System.out.println("获取方法");
        System.out.println("===============================");
        //2.获取方法
        Method g = c2.getDeclaredMethod("f");
        Demo demo2 = new Demo();
        g.invoke(demo2);

        Method g1 = c2.getDeclaredMethod("g", String.class);
        g1.setAccessible(true);
        g1.invoke(demo2, "haha");
        System.out.println("==============================");
        Method[] methods = c2.getMethods();
        for (Method method : methods) {
            System.out.println(method);
            String name = method.getName();
            System.out.println(name);
            method.setAccessible(true);
        }

    }
}
