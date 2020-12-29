package top.veritasal.genericitylearn.demoerasure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        ArrayList<Integer> intList = new ArrayList<>();
        ArrayList<String> strList = new ArrayList<>();

        //类型擦除
        System.out.println(intList.getClass().getSimpleName());
        System.out.println(strList.getClass().getSimpleName());

        System.out.println(intList.getClass() == strList.getClass());
        System.out.println("======================");
        //无限制类型擦除，转换为Object类
        Erasure<Integer> erasure = new Erasure<>();
        //利用反射，获取Erasure类的字节码文件的Class类对象
        Class<? extends Erasure> clz = erasure.getClass();
        //获取所有成员变量
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName()  + ": " + declaredField.getType().getSimpleName());
        }

        System.out.println("=======================");

        Method[] declaredMethods = clz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName() + ": " + declaredMethod.getReturnType().getSimpleName());
        }

        System.out.println("=======================");

        Class<InfoImpl> infoClass = InfoImpl.class;
        //获取所有方法
        Method[] declaredMethods1 = infoClass.getDeclaredMethods();
        for (Method method : declaredMethods1) {
            System.out.println(method.getName() + ": " + method.getReturnType().getSimpleName());
        }
    }
}
