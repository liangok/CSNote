package top.veritasal.collectiontest;

import java.util.*;

public class CollectionStudy {
    public static void main(String[] args) {
        String[] a = new String[]{"haha", "lala"};
        List<String> stringIterable = new ArrayList<String>(Arrays.asList(a));
        stringIterable.add("blabla");
        stringIterable.add("youdao");
        Iterator iterable = stringIterable.iterator();
        while (iterable.hasNext()){
            System.out.println(iterable.next());
        }

        List<Integer> integerSet = new Stack<>();
        integerSet.add(1);
        integerSet.add(1);
        integerSet.add(4);

        Stack<Integer> integers = new Stack<>();

        Set<Integer> integers1 = new HashSet<>();
        integers1.add(3);
        integers1.add(3);
        integers.peek();

    }
}

