package com.diliprathore.java;

public class FirstThread {

    public static void main(String[] args) {
        System.out.println("before starting the thread -> " + Thread.currentThread().getName());

	    Thread thread = new Thread(() -> {
            System.out.println("we are now in thread " + Thread.currentThread().getName());
            System.out.println("thread priority is " + Thread.currentThread().getPriority());
        });
        thread.setName("new worker thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();

        System.out.println("after starting the thread -> " + Thread.currentThread().getName());
    }
}
