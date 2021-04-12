package com.example.thread;

/**
 * @author wsHawk
 * @Title: ThreadPriorityDemo
 * @ProjectName just-for-test
 * @Description:
 * @since 2021/4/12 21:28
 */
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        Thread a = new Thread();
        System.out.println("我是默认线程优先级："+a.getPriority());
        Thread b = new Thread();
        b.setPriority(10);
        System.out.println("我是设置过的线程优先级："+b.getPriority());
    }
}
