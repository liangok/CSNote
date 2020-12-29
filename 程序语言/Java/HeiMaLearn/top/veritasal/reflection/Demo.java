package top.veritasal.reflection;

public class Demo {
    private String s = "Hello";
    private int num = 11;

    public double number = 2.5;

    public Demo(){}

    public Demo(String s, int num) {
        this.s = s;
        this.num = num;
    }

    public void f(){
        System.out.println("This is demo.Demo f()");
    }

    private void g(String st){
        System.out.println("This is demo.Demo g()" + st);

    }


    @Override
    public String toString() {
        return "Demo{" +
                "s='" + s + '\'' +
                ", num=" + num +
                ", number=" + number +
                '}';
    }
}

