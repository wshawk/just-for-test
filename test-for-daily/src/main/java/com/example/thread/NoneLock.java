package com.example.thread;

/**
 * @author hawk
 * @package com.example.thread
 * @desc
 * @date 2021/4/15
 */
public class NoneLock {
        static class ThreadA implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread A " + i);
                }
            }
        }

        static class ThreadB implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread B " + i);
                }
            }
        }

        public static void main(String[] args) {
            new Thread(new ThreadA()).start();
            new Thread(new ThreadB()).start();
        }
}
