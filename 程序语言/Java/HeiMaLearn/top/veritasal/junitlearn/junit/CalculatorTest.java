package top.veritasal.junitlearn.junit;

public class CalculatorTest {
    public static void main(String[] args) {
        //创建对象
        Calculator calculator = new Calculator();

        //调用
        int result = calculator.add(1, 2);
        System.out.println(result);

        int sub = calculator.sub(4, 3);
        System.out.println(sub);


    }
}
