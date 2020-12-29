package top.veritasal.junitlearn.junit;

import java.util.Scanner;

public class YearJudge {

    public static Boolean isLeapYear(int year){
        if(year > 0 && year <= 10000 && ((year % 100 != 0 && year % 4 ==0) | (year % 100 == 0
        && year % 400 == 0))){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入年份");
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        if(isLeapYear(year)){
            System.out.println("是闰年");
        }else {
            System.out.println("不是闰年");
        }
    }

}
