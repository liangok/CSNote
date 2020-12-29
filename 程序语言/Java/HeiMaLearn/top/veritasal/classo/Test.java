package top.veritasal.classo;

import java.util.*;


public class Test {
    /**
     * 联合ArrayList
     * @param i1
     * @param i2
     * @return
     */
    public static ArrayList<Integer> union (ArrayList<Integer> i1, ArrayList<Integer> i2){
        ArrayList<Integer> arrayList = new ArrayList<>(i1);
        arrayList.addAll(i2);
        return arrayList;
    }

    /**
     * 排序
     * @param list
     */
    public static void sort(ArrayList<Integer> list){
        list.sort(new MyComparator());
    }


    /**
     * 求和
     * @param list
     * @return
     */
    public static double sum(ArrayList<Double> list){
        double sum = 0;
        Iterator<Double> iterator = list.iterator();
        while(iterator.hasNext()){
            sum += iterator.next();
        }

        return sum;
    }

    /**
     * 去掉重复的元素
     * @param list
     */
    public static void removeDuplicate(ArrayList<Integer> list){
        //或者用一个数组记录所有的list中的值，再对list进行遍历，如果发现重复的就用.remove()
        TreeSet<Integer> treeSet = new TreeSet<>(list);
        list = new ArrayList<Integer>(treeSet);
    }

    public static void main(String[] args) {
        ArrayList<Integer> i1 = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
        ArrayList<Integer> i2 = new ArrayList<>(Arrays.asList(5,6,7,8));
        System.out.println(union(i1, i2));
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(2, 3, 4, 5, 6, 3, 32, 2, 43, 4));
        System.out.println(        linkedList.offer(4));
    }
}


class MyComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}