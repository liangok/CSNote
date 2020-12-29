package top.veritasal.genericitylearn.demointerface;

public class MainClass {
    public static void main(String[] args) {
        Apple apple = new Apple();
        String key = apple.getKey();
        System.out.println("key = " + key);

        System.out.println("======================");

        Pair<String, Integer> pair = new Pair<>("count", 100);
        String key1 = pair.getKey();
        Integer value = pair.getValue();
        System.out.println("value = " + value);
        System.out.println("key1 = " + key1);
    }
}
