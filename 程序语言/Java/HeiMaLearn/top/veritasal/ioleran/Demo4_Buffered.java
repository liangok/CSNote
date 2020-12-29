package top.veritasal.ioleran;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Demo4_Buffered {
    public static void main(String[] args) throws IOException {
        demo1();

    }

    /**
     *
     * @throws IOException
     */
    private static void demo1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\liang\\IdeaProjects\\GenericityDemo\\src\\top\\veritasal\\ioleran\\xxx.txt"));
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }

        br.close();
    }
}
