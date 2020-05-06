package com.fhlxc.concurrentprogramming;

/**
* @author Xingchao Long
* @date 2020年4月3日 下午8:44:22
* @classname DeadLock
* @description 
*/

public class DeadLock {

    private static String A = "A";
    private static String B = "B";
    
    public static void main(String[] args) {
        new DeadLock().deadLock();
    }
    
    public void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }

}
