package top.veritasal.multithreading.thinkjava.democallable;

import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * 与Runnable类似不过有返回类型
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}
