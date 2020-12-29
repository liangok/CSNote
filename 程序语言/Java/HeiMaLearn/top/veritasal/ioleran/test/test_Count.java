package top.veritasal.ioleran.test;

import java.io.*;
import java.util.TreeMap;

public class test_Count {
    /**
     * 获取一个文本上每个字符出现的次数，把结果写在times.txt
     *
     * 分析
     * 1.创建带缓冲的输入流对象
     * 2.创建双列结合对象TreeMap
     * 3.将读到的字符存在双列集合中，存储的时候要做判断，如果不包含这个键，就将这个键和1存储，如果包含这个键，就将该键和值加1存储
     * 4.关闭输入流
     * 5.创建输出流对象
     * 6.遍历结合将集合中的内容写道times.txt
     * 7.关闭输出流
     *
     */

    public static void main(String[] args) throws IOException {
        //1.创建带缓冲的输入流对象
        BufferedReader br = new BufferedReader(new FileReader("zzz.txt"));

        //2.创建双列结合对象TreeMap
        TreeMap<Character, Integer> tm = new TreeMap<>();

        //3.将读到的字符存在双列集合中，存储的时候要做判断，如果不包含这个键，就将这个键和1存储，如果包含这个键，就将该键和值加1存储
        int ch;
        while ((ch = br.read()) != -1){
            char c = (char)ch;
            /*if(!tm.containsKey(c)){
                tm.put(c, 1);
            }else {
                tm.put(c, tm.get(c) + 1);
            }*/
            tm.put(c, !tm.containsKey(c) ? 1 : tm.get(c) + 1);
        }

        //4.关闭输入流
        br.close();

        //5.创建输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("times.txt"));

        //6.遍历结合将集合中的内容写道times.txt
        for (Character character : tm.keySet()) {
            bw.write(character + "=" + tm.get(character));
            bw.newLine();
        }

        //7.关闭输出流
        bw.close();
    }
}
