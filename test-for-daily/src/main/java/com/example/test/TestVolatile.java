package com.example.test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wsHawk
 * @Title: TestVolatile
 * @ProjectName just-for-test
 * @Description: TODO
 * @since 2021/4/12 20:25
 */
public class TestVolatile {
    volatile int i = 0;
    public static void main(String[] args) {
        TestVolatile testVolatile = new TestVolatile();
        AtomicBoolean flag = new AtomicBoolean(true);
        new Thread(() ->{
            while (flag.get()){
                int j = testVolatile.getI();
                if ((j & 1) == 1){
                    System.out.println("A");
                }
                testVolatile.setI(j + 1);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if (j == 10){
                    flag.set(false);
                }
            }
        }).start();
        new Thread(() ->{
            while (flag.get()){
                int j = testVolatile.getI();
                if ((j & 1) != 1){
                    System.out.println("B");
                }
                testVolatile.setI(j + 1);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if (j == 10){
                    flag.set(false);
                }
            }
        }).start();
    }
    int getI(){
        return i;
    }
    void setI(int i){
        this.i = i;
    }
}
