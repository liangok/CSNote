package top.veritasal.ioleran.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;

public class LineCount {
    public static void main(String[] args) throws Exception {
        File path = new File("C:\\Users\\liang\\source\\repos");
        System.out.println("total: " + LineCount.Count(path));
    }
    public static int Count(File path) throws Exception {
        int lineNum = 0;
        File[] files = path.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String name = file.getName();
                if (name.endsWith(".cpp")) {
                    LineNumberReader lnr = new LineNumberReader(new FileReader(file));
                    String line;
                    while ((line = lnr.readLine()) != null){
                        lineNum++;
                    }
                    System.out.println(name + ": " + lineNum);
                }
            }else {
                lineNum += Count(file);
            }
        }
        return lineNum;
    }


}