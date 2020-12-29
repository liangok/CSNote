package top.veritasal.collectionlearn;

import java.util.*;
import jdk.internal.util.ArraysSupport;


public class MyArrayList <E> extends AbstractList<E>
    implements List<E>
{
    //https://baijiahao.baidu.com/s?id=1633305649182361563&wfr=spider&for=pc
    //与文件的读取写入有关
    //private static final long serialVersionUID;
    //空数据
    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final int DEFAULT_CAPACITY = 10;
    //默认空数据
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    //transient 关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，
    //在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。
    transient Object[] elementData;

    private int size;


    public MyArrayList(int initialCapacity){
        if(initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else {
            throw new IllegalArgumentException("Illegal Capacity:" + initialCapacity);
        }
    }


    public MyArrayList(Object[] elementData) {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }


    public MyArrayList(Collection<? extends E> c){
        elementData = c.toArray();
        if((size = elementData.length) != 0){
            // defend against c.toArray (incorrectly) not returning Object[]
            //排除c.toArray返回的类型不是Object[]
            if(elementData.getClass() != Object[].class){
                elementData = Arrays.copyOf(elementData, size, Object[].class);
            } else {
                //replace with empty array
                this.elementData = EMPTY_ELEMENTDATA;
            }
        }
    }

    /**
     * 截取长度
     */
    public void trimToSize(){
        if(size < elementData.length){
            elementData = (size == 0) ? EMPTY_ELEMENTDATA : Arrays.copyOf(elementData, size);
        }
    }

    public void ensureCapacity(int minCapacity){
        if(minCapacity > elementData.length && !(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            && minCapacity <= DEFAULT_CAPACITY){
            modCount++;
            grow(minCapacity);
        }
    }

    private Object[] grow(int minCapacity){
        int oldCapacity = elementData.length;
        if(oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        }else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    @Override
    public int size() {
        return 0;
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }
}


