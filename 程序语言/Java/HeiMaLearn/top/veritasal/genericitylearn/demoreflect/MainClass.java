package top.veritasal.genericitylearn.demoreflect;

import java.lang.reflect.Constructor;

/**
 * 泛型与反射
 */
public class MainClass {
    public static void main(String[] args) throws Exception{
        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getConstructor();
        Person person = constructor.newInstance();

        //若不用泛型，第三步会创建一个Object对象
    }
}
