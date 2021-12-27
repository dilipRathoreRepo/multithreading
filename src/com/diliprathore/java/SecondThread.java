package com.diliprathore.java;

public class SecondThread {
    public static void main(String[] args) {
        Thread thread = new NewThread();
        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            this.setName("NewThread");
            System.out.println("Hello from " + this.getName());
        }
    }
}
