package top.veritasal.ioleran;

public class Demo6_Wrap {

    public static void main(String[] args) {
        HarvardStudent havardSchool = new HarvardStudent(new Student());
        havardSchool.code();
    }
}

interface Coder{
    public void code();

}

class Student implements Coder{
    @Override
    public void code() {
        System.out.println("javaSE");
        System.out.println("javaWeb");
    }
}

class HarvardStudent implements Coder{
    //1. 获取被装饰类引用
    private Student s;  //对学生包装，先拿到这个对象

    //2. 在构造方法传入被装饰类的对象
    public HarvardStudent(Student s){
        this.s = s;
    }

    //3. 对原有的功能进行升级
    @Override
    public void code() {
        s.code();
        System.out.println("ssh");
        System.out.println("sql");
        System.out.println("编译原理");
        System.out.println("算法");
        System.out.println("计算机网络");
        System.out.println("....");
    }
}