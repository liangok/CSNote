package top.veritasal.genericitylearn.demoerasure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//public class Erasure<T> { 无限制性类型擦除
//public class Erasure<T extends Number> {  有限制性类型擦除
public class Erasure<T extends Number> {
    private T key;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    /**
     * 泛型方法
     * @param t
     * @param <T>
     * @return
     */
    public <T extends List> T show(T t){
        return t;
    }

}



