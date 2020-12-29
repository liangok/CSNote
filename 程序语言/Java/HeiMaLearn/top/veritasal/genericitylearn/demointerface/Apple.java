package top.veritasal.genericitylearn.demointerface;

/**
 * 实现类不是泛型类，接口要确定数据类型
 */
public class Apple implements Generator<String> {
    @Override
    public String getKey() {
        return "Hello Apple";
    }
}
