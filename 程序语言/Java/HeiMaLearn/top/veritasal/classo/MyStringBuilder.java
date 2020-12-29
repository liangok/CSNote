package top.veritasal.classo;

import java.util.Arrays;

public class MyStringBuilder {
    private String str;
    public MyStringBuilder(String s){
        str = s;
    }
    //toCharArray()
    public MyStringBuilder append(MyStringBuilder s){
        return new MyStringBuilder(str + s);
    }

    public MyStringBuilder append(int i){
        return new MyStringBuilder(str + i);
    }

    public int length(){
        return str.length();
    }

    public char charAt(int index){
        return str.charAt(index);
    }

    public MyStringBuilder toLowerCase(){
        return new MyStringBuilder(str.toLowerCase());
    }

    public MyStringBuilder subString(int begin, int end){
        return new MyStringBuilder(str.substring(begin, end));
    }

    public MyStringBuilder(char[] chars){
        str = chars.toString();
    }

    public MyStringBuilder insert(int offset, MyStringBuilder m){
        if(m.length() == 0){
            return this;
        }
        String tmp1 = str.substring(0, offset);
        String tmp2 = str.substring(offset, str.length());
        return new MyStringBuilder(tmp1 + m.str + tmp2);
    }
    
    @Override
    public String toString() {
        return "MyStringBuilder{" +
                "str='" + str + '\'' +
                '}';
    }
}
class Tetst{
    public static void main(String[] args) {
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("hah");
        stringBuilder.append("lal");
        System.out.println(stringBuilder);
    }
}