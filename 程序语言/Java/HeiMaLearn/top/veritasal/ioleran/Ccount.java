package top.veritasal.ioleran;

import java.io.File;
import java.util.ArrayList;

public class Ccount {
    public static void main(String[] args) {
        File path = new File("C:\\Users\\liang\\source\\repos");
        ArrayList<String> list = serarchCfile(path);
        System.out.println(list);
    }
    public static ArrayList<String> serarchCfile(File path){
        ArrayList<String> result = new ArrayList<>();
        File[] files = path.listFiles();
        for (File file : files) {
            if(file.isFile()){
                String name = file.getName();
                if(name.endsWith(".cpp")){
                    result.add(name);
                }
            }else {
                result.addAll(serarchCfile(file));
            }
        }
        return result;
    }
}
