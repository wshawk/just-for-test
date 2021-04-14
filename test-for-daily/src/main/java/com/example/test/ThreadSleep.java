package com.example.test;

/**
 * @author hawk
 * @package com.example.test
 * @desc
 * @date 2021/4/13
 */
public class ThreadSleep {
    public static void main(String[] args) throws Exception{
        Thread.sleep(20000);
        Object f = new Object();
        f.wait();
    }
}
