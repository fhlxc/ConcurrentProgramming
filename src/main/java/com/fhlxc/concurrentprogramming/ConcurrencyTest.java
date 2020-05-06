package com.fhlxc.concurrentprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* @author Xingchao Long
* @date 2020年4月3日 下午5:49:59
* @classname ConcurrencyTest
* @description 并发与串行执行时间的比较
* 1亿 快一倍
* 1千万 慢
* 1百万 慢
* 10万 慢
* 1万 慢
* 原因: 上下文切换和线程创建需要时间
*/

public class ConcurrencyTest {

    public static long count;
    
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            
            public void run() {
                @SuppressWarnings("unused")
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency: " + time + "ms, b = " + b);
    }
    
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0; 
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial: " + time + "ms, b = " + b + ", a = " + a);
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {
        count = Long.parseLong(new BufferedReader(new InputStreamReader(System.in)).readLine());
        concurrency();
        serial();
    }

}
