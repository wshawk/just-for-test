package com.example.thread;

/**
 * @author wsHawk
 * @Title: ThreadGroupDemo
 * @ProjectName just-for-test
 * @Description:
 * 每一个线程都归属某一个线程组，不能独立存在
 * 如果在new Thread时没有指定线程组，默认将父线程（当前执行new Thread的线程）线程组设置为自己的线程组
 * @since 2021/4/12 21:23
 */
public class ThreadGroupDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(() -> {
            System.out.println("testThread当前线程组名字：" +
                    Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread线程名字：" +
                    Thread.currentThread().getName());
        });
        testThread.start();
        testThread.join();
        System.out.println("执行main所在线程的线程组名字： " + Thread.currentThread().getThreadGroup().getName());
        System.out.println("执行main方法线程名字：" + Thread.currentThread().getName());
    }
}
