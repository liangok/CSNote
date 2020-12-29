package top.veritasal.junitlearn.junit.junittest;

import org.junit.Assert;
import org.junit.Test;
import top.veritasal.junitlearn.junit.YearJudge;
public class YearJudgeTest {
   @Test
    public void testIsLeapYear(){
       Integer [] years = new Integer[]{-100, 1000, 20000, 2020, 2019, 2000, 1900};
       Boolean[] answer = new Boolean[]{false, false, false, true, false, true, false};
       for (int i = 0; i < years.length; i++) {
           System.out.print("第 " + i + " 个 ");
           Assert.assertEquals(answer[i], YearJudge.isLeapYear(years[i]));
           System.out.println("正确");
       }

   }

}
