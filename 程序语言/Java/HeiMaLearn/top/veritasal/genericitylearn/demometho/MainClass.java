package top.veritasal.genericitylearn.demometho;
import top.veritasal.genericitylearn.productgetter.ProductGetter;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        ProductGetter<Integer> productGetter = new ProductGetter<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("笔记本电脑");
        stringArrayList.add("苹果手机");
        stringArrayList.add("扫地机器人");
        stringArrayList.add("ipad pro");

        //泛型方法的调用，类型通过调用方法的时候来指定
        String product1 = productGetter.getProduct(stringArrayList);
        System.out.println("product1 = " + product1 + "\t" + product1.getClass().getSimpleName());

        System.out.println("======================");
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(10000);
        integerArrayList.add(20000);
        integerArrayList.add(40000);
        integerArrayList.add(50000);
        Integer product2 = productGetter.getProduct(integerArrayList);
        System.out.println("product2 = " + product2 + "\t" + product2.getClass().getSimpleName());
    }


}
