package com.example.thread;

import java.util.concurrent.*;

/**
 * @author wsHawk
 * @Title: TaskDemo1
 * @ProjectName just-for-test
 * @Description:  自定义Callable
 * @since 2021/4/12 21:15
 */
public class TaskDemo1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            // 模拟计算需要一秒
            Thread.sleep(1000);
            return 2;
        }
        public static void main(String args[]) throws Exception {
            // 使用
//            ExecutorService executor = Executors.newCachedThreadPool();
//            TaskDemo1 task = new TaskDemo1();
//            Future<Integer> result = executor.submit(task);
//            // 注意调用get方法会阻塞当前线程，直到得到结果。
//            // 所以实际编码中建议使用可以设置超时时间的重载get方法。
//            System.out.println(result.get());
            // 使用
            ExecutorService executor = Executors.newCachedThreadPool();
            FutureTask<Integer> futureTask = new FutureTask<>(new TaskDemo1());
            executor.submit(futureTask);
            System.out.println(futureTask.get());
        }
}
