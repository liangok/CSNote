package top.veritasal.genericitylearn.demoselect.extendstest;

import java.util.Comparator;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        TreeSet<Cat> treeSet = new TreeSet<>(new Comparator1());
        //不可以用Comparator3()，因为default Comparator<T> thenComparing(Comparator<? super T> other) {
        //在构造方法中用的是通配符上限
        treeSet.add(new Cat("Jerry", 20));
        treeSet.add(new Cat("Amy", 21));
        treeSet.add(new Cat("Frank", 22));
        treeSet.add(new Cat("Jim", 23));
        treeSet.add(new Cat("Eric", 24));
        for (Cat cat : treeSet) {
            System.out.println(cat);
        }
    }
}

/**
 * 按名字排序
 */
class Comparator1 implements Comparator<Animal>{

    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.name.compareTo(o2.name);
    }
}

/**
 * 按年龄排序
 */
class Comparator2 implements Comparator<Cat>{
    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.age - o2.age;
    }
}

/**
 * 按等级排序
 */
class Comparator3 implements Comparator<MiniCat>{
    @Override
    public int compare(MiniCat o1, MiniCat o2) {
        return o1.level - o2.level;
    }
}
