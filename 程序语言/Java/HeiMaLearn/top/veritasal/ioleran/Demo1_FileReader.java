package top.veritasal.ioleran;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Demo1_FileReader {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //demo1();
        FileReader fr = new FileReader("C:\\Users\\liang\\IdeaProjects\\GenericityDemo\\src\\top\\veritasal\\ioleran\\xxx.txt");
        int c;
        while((c = fr.read()) != -1){
            System.out.println((char)c);
        }

        fr.close();
    }

    private static void demo1() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\liang\\IdeaProjects\\GenericityDemo\\src\\top\\veritasal\\ioleran\\xxx.txt");
        int x = fr.read();
        char c = (char)x;
        System.out.println(c);
        fr.close();
    }
}
