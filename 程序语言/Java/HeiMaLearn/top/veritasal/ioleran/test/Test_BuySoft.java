package top.veritasal.ioleran.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test_BuySoft {
    /**
     *
     * @param args
     * @throws IOException
     *
     * 1创建带缓冲的输入流对象，因为要使用ReadLine()方法，可以保持对数据的原样性
     * 2.将读到的字符转化为int数
     * 3.对int数进行判断，大于0将其一一写回去，否则提醒购买正版
     *
     */
    public static void main(String[] args) throws IOException {
        System.out.println("软件试用版：");
        BufferedReader br = new BufferedReader(new FileReader("config.txt"));
        String line = br.readLine();

        int times = Integer.parseInt(line);

        if(times >= 0){
            System.out.println("你还有" + times-- + "次机会");
            FileWriter fw = new FileWriter("config.txt");
            fw.write(times + "");
            fw.close();
        }else {
            System.out.println("试用次数以到");
        }
    }


}
