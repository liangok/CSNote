package top.veritasal.genericitylearn.demoarray;

import java.lang.reflect.Array;

public class Fruit<T> {
    //不可以这样写，因为还不知道T是什么类型
    //private T[] array = new T[3];

    private T[] array;

    public Fruit(Class<T> clz, int length) {
        //通过Array.newInstance创建泛型数组
        array = (T[])Array.newInstance(clz, length);
    }

    /**
     * 放入数组元素
     * @param index
     * @param item
     */
    public void put(int index, T item) {
        array[index] = item;
    }

    /**
     * 获取数组元素
     * @param index
     * @return
     */
    public T get(int index){
        return array[index];
    }
    public T[] getArray() {
        return array;
    }
}
