package top.veritasal.genericitylearn.demoextend;

public class MainClass {
    public static void main(String[] args) {
        ChildFirst<String> stringChildFirst = new ChildFirst<>();
        stringChildFirst.setValue("abc");
        String firstValue = stringChildFirst.getValue();
        System.out.println("firstValue = " + firstValue);
        System.out.println("===========================");

        ChildSecond childSecond = new ChildSecond();
        childSecond.setValue(100);
        Integer secondValue = childSecond.getValue();
        System.out.println("secondValue = " + secondValue);
    }

}
