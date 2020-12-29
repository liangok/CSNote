package top.veritasal.genericitylearn.demoarray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 泛型与数组
 */
public class MainClass {
    public static void main(String[] args){
        //!ArrayList<String>[] lstArray = new ArrayList<String>[5];
        //ArrayList[] list = new ArrayList[5];
        //ArrayList<String>[] listArray = list;

        ArrayList<String>[] listArray = new ArrayList[5];

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(100);

        ArrayList<String> strList = new ArrayList<>();
        strList.add("Abc");

        listArray[0] = strList;
        //listArray[0] = intList[0];
        //list[0] = intList;
        String s = listArray[0].get(0);
        System.out.println(s);

        System.out.println("=====================");

        Fruit<String> fruit = new Fruit<>(String.class, 3);
        fruit.put(0, "苹果");
        fruit.put(1, "橘子");
        fruit.put(2, "香蕉");
        System.out.println(Arrays.toString(fruit.getArray()));
    }
}
