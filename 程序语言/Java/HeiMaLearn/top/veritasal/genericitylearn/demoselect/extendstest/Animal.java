package top.veritasal.genericitylearn.demoselect.extendstest;

public class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
