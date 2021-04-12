package com.example.thread;

/**
 * @author wsHawk
 * @Title: TestThreadGroupException
 * @ProjectName just-for-test
 * @Description: 线程组的统一异常处理
 * @since 2021/4/12 21:35
 */
public class TestThreadGroupException {
    public static void main(String[] args) {
        ThreadGroup threadGroup1 = new ThreadGroup("group1") {
            // 继承ThreadGroup并重新定义以下方法
            // 在线程成员抛出unchecked exception
            // 会执行此方法
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        };

        // 这个线程是threadGroup1的一员
        Thread thread1 = new Thread(threadGroup1, new Runnable() {
            public void run() {
                // 抛出unchecked异常
                throw new RuntimeException("测试异常");
            }
        });

        thread1.start();

        TestThreadGroupException p = new TestThreadGroupException(){
            public void test(){
                System.out.println("aaa");
            }
        };
        p.test();
    }

    public void test(){
        System.out.println("bbb");
    }
}
