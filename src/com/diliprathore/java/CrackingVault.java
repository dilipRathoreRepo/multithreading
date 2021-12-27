package com.diliprathore.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrackingVault {
    public static final int MAX_PASSWORD = 999;
    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        AscendingHackerThread ascendingHackerThread = new AscendingHackerThread(vault);
        DescendingHackerThread descendingHackerThread = new DescendingHackerThread(vault);
        PoliceThread policeThread = new PoliceThread();

        List<Thread> threads = new ArrayList<>();
        threads.add(ascendingHackerThread);
        threads.add(descendingHackerThread);
        threads.add(policeThread);

        for (Thread thread: threads) {
            thread.start();
        }
    }
    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int password) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return password == this.password;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int i = 0; i <= MAX_PASSWORD; i++) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println("This thread "+ this.getName() + " guessed the password " + i + " correctly");
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int i = MAX_PASSWORD; i >= 0; i--) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println("This thread " + this.getName() + " guessed the password " + i + " correctly");
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println(i);
            }
            System.out.println("Game over for hackers");
            System.exit(0);
        }
    }

}
