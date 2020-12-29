package top.veritasal.genericitylearn.demoselect.normal;

public class MainClass {
    public static void main(String[] args) {
        Box<Number> box1 = new Box<>();
        box1.setFirst(100);
        Box<Integer> integerBox = new Box<>();
        integerBox.setFirst(200);
        showBox(box1);
        showBox(integerBox);
    }

    //类型通配符的上限
    public static void showBox(Box<? extends Number> box){
        Object first = box.getFirst();
        System.out.println(first);
    }

//    "?"通配符
//    public static void showBox(Box<?> box){    //在<>里放Number时，无法接收integerBox，可以用问号
//        Object first = box.getFirst();  //注意Object的类来接收
//        System.out.println(first);
//    }

//    不可以写两个这样的方法，因为它们的形参的类实际上是一样的
//    public static void showBox(Box<Integer> box){
//        Integer first = box.getFirst();
//        System.out.println(first);
//
//    }
}
