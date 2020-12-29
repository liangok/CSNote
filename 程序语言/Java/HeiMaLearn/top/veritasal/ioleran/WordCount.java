package top.veritasal.ioleran;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WordCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\liang\\Desktop\\The Unbearable Lightness of Being.txt"));
        String str = "";
        HashMap<String, Integer> hashMap = new HashMap<>();
        while ((str = br.readLine()) != null){
            String[] strings = str.split("\\W+");
            for (int i = 0; i < strings.length; i++) {
                if(hashMap.containsKey(strings[i])){
                    Integer value = hashMap.get(strings[i]);
                    value++;
                    hashMap.put(strings[i], value);
                }else {
                    hashMap.put(strings[i], 1);
                }
            }
        }

        /*Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            System.out.println("单词：" + iterator.next() + " 个数：" + hashMap.get(iterator.next()));
        }*/


        Iterator<Map.Entry<String, Integer>> iterator = hashMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println("单词：" + next.getKey() + " 个数：" + next.getValue());
        }
    }
}
