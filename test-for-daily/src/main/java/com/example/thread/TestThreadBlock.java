package com.example.thread;

import org.junit.Test;

/**
 * @author hawk
 * @package com.example.thread
 * @desc
 * @date 2021/4/14
 */
public class TestThreadBlock {
    @Test
    public void blockedTest() throws InterruptedException{

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
        // Thread.sleep(1000L);
        a.join();
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
    }

    // 同步方法争夺锁
    private synchronized void testMethod() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
