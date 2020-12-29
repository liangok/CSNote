package top.veritasal.junitlearn.junit.junittest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import top.veritasal.junitlearn.junit.Calculator;

public class CalculateTest {

    /**
     * 初始化方法
     * 用于资源申请，所有测试方法在执行前都会先执行该方法
     */
    @Before
    public void init(){
        System.out.println("init...");
    }

    /**
     * 释放资源方法
     * 在所有测试方法执行完后，都会自动执行该方法
     */
    @After
    public void close(){
        System.out.println("close");
    }
    /**
     * 测试add方法
     */
    @Test
    public void testadd(){
        //System.out.println("我被执行了");
        //创建计算器对象
        Calculator c = new Calculator();
        //调用add方法
        int result = c.add(1, 2);
        //System.out.println(result);

        //3.断言 我断言这个结果是3，与真正的结果对比
        Assert.assertEquals(3, result);

    }

    @Test
    public void testsub(){
        Calculator c = new Calculator();
        int result = c.sub(4, 3);
        System.out.println("Testsub");
        Assert.assertEquals(1, result);
    }
}
