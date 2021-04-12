package com.example.thread;

/**
 * @author wsHawk
 * @Title: MyThread
 * @ProjectName just-for-test
 * @Description: 多线程学习Demo1
 * @since 2021/4/12 21:10
 */
public class Demo1 {
    public static class MyThread implements Runnable{

        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {
        new Thread(new MyThread()).start();

        // Java 8 函数式编程，可以省略MyThread类
        new Thread(() -> {
            System.out.println("Java 8 匿名内部类");
        }).start();
    }
}
