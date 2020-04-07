package com.fhlxc.concurrentprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
* @author Xingchao Long
* @date 2020年4月7日 下午9:11:04
* @classname Count
* @description 利用CAS实现安全的count
*/

public class Count {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private int i = 0;
    
    public static void main(String[] args) {
        final Count cas = new Count();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.safeCount();
                        cas.count();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t: ts) {
            t.start();
        }
        for (Thread t: ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }
    
    private void safeCount() {
        for (;;) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }
    
    private void count() {
        i++;
    }

}
