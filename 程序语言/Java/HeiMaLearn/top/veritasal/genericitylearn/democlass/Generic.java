package top.veritasal.genericitylearn.democlass;

/**
 *
 * @param <T>   泛型类标识符--形参类型
 *              T创建对象的时候里指定具体的数据类型
 */
public class Generic <T>{
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
