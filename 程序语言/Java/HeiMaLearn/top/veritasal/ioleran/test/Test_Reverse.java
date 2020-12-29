package top.veritasal.ioleran.test;

import java.io.*;
import java.util.ArrayList;

public class Test_Reverse {

    /**
     * 将文本上的文本反转，第一行和倒数第一行交换
     * @param args
     * 1.创建输出流对象
     * 2.创建集合对象
     * 3.到这将读的数据存在集合中
     * 4.到这遍历集合将数据写到文件上
     * 5.关流
     *
     * 注意：尽量晚开早关
     */
    public static void main(String[] args) throws IOException {

        //1.创建输出流对象
        BufferedReader br = new BufferedReader(new FileReader("xxx.txt"));

        //2.创建集合对象
        ArrayList<String> arrayList = new ArrayList<>();

        //3.到这将读的数据存在集合中
        String line;
        while ((line = br.readLine()) != null){
            arrayList.add(line);
        }
        br.close();

        //4.到这遍历集合将数据写到文件上
        BufferedWriter bw = new BufferedWriter(new FileWriter("xx.txt"));
        for(int i = arrayList.size(); i >= 0; i--){
            bw.write(arrayList.get(i));
            bw.newLine();
        }

        //5.关流

        bw.close();

    }
}
