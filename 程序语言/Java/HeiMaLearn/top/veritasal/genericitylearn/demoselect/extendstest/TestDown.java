package top.veritasal.genericitylearn.demoselect.extendstest;

import java.util.ArrayList;
import java.util.List;

public class TestDown {
    public static void main(String[] args){
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<MiniCat> miniCats = new ArrayList<>();

        //!cats.addAll(animals);
        cats.addAll(cats);
        cats.addAll(miniCats);

        showAnimal(animals);
        showAnimal(cats);
        //!showAnimal(miniCats);
    }

    /**
     * 类型通配符下限，要求集合二只能是Cat或者Cat的夫类型
     * @param list
     */
    public static void showAnimal(List<? super Cat> list){
        //通配符下限中可以添加新的对象，因为他可以传入的是子类，因为在创建子类时会自动创建父类的对象
        //!list.add(new Animal());
        /*list.add(new Cat());
        list.add(new MiniCat());*/
        for (Object o: list
             ) {
            System.out.println(o);
        }
    }
}
