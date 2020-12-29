package top.veritasal.genericitylearn.demoextend;

/**
 * 泛型派生子类，那么子类的泛型标识要与父类一致
 * @param <T>
 */
public class ChildFirst<T> extends Parent<T> {
    //!public class ChildFirst<E> extends Parent<T>
    //public class ChildFirst<T, E> extends Parent<T>  is available
    @Override
    public T getValue() {
        return super.getValue();
    }
}

