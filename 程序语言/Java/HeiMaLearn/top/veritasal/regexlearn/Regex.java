package top.veritasal.regexlearn;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex{
    public static void main(String[] args) {
        //返回正则表达式的字符串形式
        Pattern p1 = Pattern.compile("\\w+");
        String pattern = p1.pattern();
        System.out.println(pattern);

        System.out.println("======================");

        //分割字符串，返回一个String[]
        Pattern p2 = Pattern.compile("\\d+");
        String[] split = p2.split("我的QQ是:2017931059我的电话是:15700980373我的邮箱是:aaa@aaa.com");
        for (String s : split) {
            System.out.println(s);
        }

        //静态方法,用于快速匹配字符串,该方法将会匹配全部字符串
        Boolean b1 = Pattern.matches("\\d+","2223");
        Boolean b2 = Pattern.matches("\\d+","2223aa");//返回false,需要匹配到所有字符串才能返回true,这里aa不能匹配到
        Boolean b3 = Pattern.matches("\\d+","22bb23");//返回false,需要匹配到所有字符串才能返回true,这里bb不能匹配到
        System.out.println(b1 + " " + b2 + " " + b3 + " ");
        /**
         * Matcher类提供三个匹配操作方法,三个方法均返回boolean类型,当匹配到时返回true,没匹配到则返回false。
         */
        //matches()方法匹配的是整个字符串
        Pattern p3 = Pattern.compile("\\d+");
        Matcher m1 = p3.matcher("22bb23");
        System.out.println("m1 = " + m1);
        Matcher m2 = p3.matcher("232423");
        System.out.println("m2 = " + m2);

        //Matcher.find()对指定的字符串进行匹配，匹配到的字符串可以在任意位置。
        Pattern p4 = Pattern.compile("\\d+");
        Matcher m3 = p4.matcher("22bb32");
        System.out.println(m3.find());
        Matcher m4 = p4.matcher("aabb");
        System.out.println("m4 = " + m4);

        //2.4 Matcher.start()返回匹配到的子字符串在字符串中的起始位置。
        Pattern p5 = Pattern.compile("\\d+");
        Matcher m5 = p5.matcher("aaa342");
        int start = m5.start();
        System.out.println("start = " + start);

        // 2.5 Matcher.end()返回匹配到的子字符串的最后一个字符的下一个索引值。注意！是下一个索引值，而不是该子字符串中最后一个字符的索引值！
        Pattern p6 = Pattern.compile("\\d+");
        Matcher m6 = p6.matcher("aaa2223bb");
        int end = m6.end();
        System.out.println("end = " + end);

        //2.6 Matcher.group() group即返回匹配到的字符串。
        Pattern p7 = Pattern.compile("\\d+");
        Matcher m7 = p7.matcher("aaa2233bb");
        String group = m7.group();
        System.out.println("group = " + group);

        //Matcher.start(int, i), Matcher.end(int, i), Matcher.group(int, i), Matcher.groupCount()
        //
        //前三个API主要用于分组操作，取出第i组数据的索引值，groupCount()则用来返回一共匹配到多少组。
        Pattern p8 = Pattern.compile("([a-z]+)(\\\\d+)");   ////()表示分组，意义是括号内是一个整体
        Matcher m8 = p8.matcher("aaa2233bb");
        m8.find();
        m8.groupCount();
        m8.start(1);
        m8.start(2);
        m8.end(1);
        m8.end(2);
        m8.group(1);
        m8.group(2);

        Pattern pattern1 = Pattern.compile("\\d+");
        Matcher matcher1 = pattern1.matcher("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com");
        while (matcher1.find()){
            System.out.println(matcher1.group());
        }
        while (matcher1.find()){
            System.out.println(matcher1.group());
            System.out.println("start:" + matcher1.start());
            System.out.println(" end:" + matcher1.end());
        }
        //注意！只有当匹配成功后，才可以使用start(), end(), group()方法，否则会抛出java.lang.IllegalStateException。
        // 也就是说，当matchers(), lookingAt(), find() 其中一个方法返回true时,才可以使用start(),end(),group()方法。
    }
}

