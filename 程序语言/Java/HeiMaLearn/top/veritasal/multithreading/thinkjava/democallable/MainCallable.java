package top.veritasal.multithreading.thinkjava.democallable;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainCallable {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result = new ArrayList<Future<String>>();
        System.out.println("Stating!");
        for (int i = 0; i < 5; i++) {
            result.add(exec.submit(new TaskWithResult(i))); //submit()方法会产生Future对象，用Callable对象作为参数
        }
        for (Future<String> fs : result) {
            System.out.println("1");
            try {
                //可以用isDone()方法来查询Future是否已经完成，若完成时，会有一个结果，可以不用isDone()检查就用get()获得结果
                //这种情况下，get()将阻塞，直到结果准备就绪
                /*if(fs.isDone()){
                    System.out.println(fs.get());
                }*/
                //System.out.println(fs.isDone());
                System.out.println(fs.get());
            }catch (InterruptedException e){
                System.out.println(e);
                return;
            }catch (ExecutionException e){
                System.out.println(e);
            }finally {
                exec.shutdown();
            }
        }
        System.out.println("Finishing");
    }
}
