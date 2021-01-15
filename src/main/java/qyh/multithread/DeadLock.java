package qyh.multithread;

import java.util.concurrent.locks.Lock;

/**
 * 死锁示范
 */
public class DeadLock {
    public static void main(String[] args) {
        final Object a = new Object();
        final Object b = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    try {
                        System.out.println("A lock t1");
                        Thread.sleep(1000l);
                        synchronized (b) {
                            System.out.println("B lock t1");
                        }
                    } catch (InterruptedException e) {
                        System.out.println("aaaa");
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (b) {
                try {
                    System.out.println("B lock t2");
                    Thread.sleep(1000l);
                    synchronized (a) {
                        System.out.println("A lock t2");
                    }
                } catch (InterruptedException e) {
                    System.out.println("bbb");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
