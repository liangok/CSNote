package top.veritasal.ioleran;

import javax.annotation.processing.Filer;
import java.io.*;

public class Demo3_Copy {
    public static void main(String[] args) throws IOException {
        //Demo1();
        //字符流不可以读取图片或音频，因为字符流再查不到对应的码表时会用？代替。会破坏源文件
        //Demo2();
        //Demo3()

    }

    /**
     * 带缓冲区的读写
     * @throws IOException
     */
    private static void Demo3() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\liang\\IdeaProjects\\GenericityDemo\\src\\top\\veritasal\\ioleran\\xxx.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("zzz.txt"));
        //BufferedReader 的缓冲区为8K
        int c;
        while((c = br.read()) != -1){
            bw.write(c);
        }

        br.close();
        bw.close();
    }

    /**
     * 自定义字符数组的拷贝
     * @throws IOException
     */
    private static void Demo2() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\liang\\IdeaProjects\\GenericityDemo\\src\\top\\veritasal\\ioleran\\xxx.txt");
        FileWriter fw = new FileWriter("zzz.txt");

        char[] arr = new char[1024 * 8];
        int len;
        while ((len = fr.read(arr)) != -1){
            fw.write(arr, 0, len);
        }

        fr.close();
        fw.close();
    }

    /**
     *
     * @throws IOException
     */
    private static void Demo1() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\liang\\IdeaProjects\\GenericityDemo\\src\\top\\veritasal\\ioleran\\xxx.txt");
        FileWriter fw = new FileWriter("zzz.txt");
        int c;
        while ((c = fr.read()) != -1){
            fw.write(c);
        }
        fr.close();
        fw.close();
        //如果没有close则zzz.txt不会显示内容，而是会放在缓冲区里，所以要记得关流
        //Writer类有一个2K的小缓冲区
    }
}
