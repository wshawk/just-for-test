package com.example.thread;

import org.junit.Test;

import java.util.concurrent.Callable;

/**
 * @author hawk
 * @package com.example.thread
 * @desc
 * @date 2021/4/14
 */
public class TestCallableDemo {
    @Test
    public void call() throws Exception {
        class TestCallable implements Callable<Object> {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Object call() throws Exception {
                Integer a = 10;
                return a*a;
            }
        }

        TestCallable t = new TestCallable();
        System.out.println(t.call());
    }
}
