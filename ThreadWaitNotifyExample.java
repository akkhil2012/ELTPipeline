package testPackage;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadWaitNotifyExample {

    public static void main(String args[]) throws InterruptedException {

        Object lock = new Object();

        AtomicInteger count= new AtomicInteger();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(" Thread 1 entered .......");
                try {
                    int i=0;
                    while (i<5) {
                        count.getAndIncrement();
                        i++;
                    }
                    System.out.println("Count in thread 1 " + count);
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println(" Thread 1 NOTIFIED  by Thread 2 .......");
                    throw new RuntimeException(e);
                }
                System.out.println(" Thread 1 resumed .......");
            }
            System.out.println(" wait notify example ends....");

        }).start();




        new Thread(() -> {
                synchronized (lock) {
                    System.out.println(" Thread 2 entered .......");
                    int i=0;
                    while (i<5) {
                        count.getAndIncrement();
                        i++;
                    }
                    System.out.println("Count in thread 2.. " + count);
                    lock.notify();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(" Thread 2 resumed .......");
                }
        }).start();


        System.out.println("Count is " + count);


    }

}
