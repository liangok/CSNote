package top.veritasal.genericitylearn.democlass;

public class MainClass {
    public static void main(String[] args) {
        //泛型类再创建对象的时候，来指定操作的具体数据类型
        Generic<String> stringGeneric = new Generic<>("abc");
        String key1 = stringGeneric.getKey();
        System.out.println("key1:" + key1);

        System.out.println("====================================");

        Generic<Integer> integerGeneric = new Generic<>(123);
        Integer key2 = integerGeneric.getKey();
        System.out.println(key2);
        System.out.println("key2 = " + key2);


        System.out.println("=====================================");
        //泛型类再创建对象时，没有指定类型，将按Object类
        Generic generic = new Generic("KEY");
        Object key = generic.getKey();
        System.out.println("generic = " + generic);

        System.out.println("===================================");
        //泛型类，不支持基本数据类型
        //!Generic<int> generic1 = new Generic<>();
        System.out.println(integerGeneric.getClass());
        System.out.println(stringGeneric.getClass());
        System.out.println(integerGeneric.getClass() == stringGeneric.getClass());


    }
}
