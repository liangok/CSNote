package top.veritasal.genericitylearn.demoselect.extendstest;

import java.util.ArrayList;

public class TestUp {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<MiniCat> miniCats = new ArrayList<>();

        //上限通配符的应用(public boolean addAll(Collection<? extends E> c))
        cats.addAll(cats);
        cats.addAll(miniCats);
        //!showAnimal(animals); animals不是Cat的子类对象
        showAnimal(cats);
        showAnimal(miniCats);
    }

    /**
     * 泛型上限通配符，传递的集合类型，只能是Cat或者是Cat的子类类型
     * @param list
     */
    public static void showAnimal(ArrayList<? extends Cat> list){
        //在使用上限通配符时，不可以在里面添加新的对象，因为你不知道实际的类到底是那一个
        //比如你实际用的是MinCat但是却加了一个Cat，就会不认识
        //!list.add(new Animal());
        //!list.add(new Cat());
        //!list.add(new MiniCat());
        for (int i = 0; i < list.size(); i++) {
            Cat cat = list.get(i);
            System.out.println(cat);
        }
    }
}
