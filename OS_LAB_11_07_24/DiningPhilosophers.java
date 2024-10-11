package OS_LAB_11_07_24;

import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private final int id;
    private final Semaphore[] forks;
    private final Semaphore maxDiners;
    private final int iterations;

    public Philosopher(int id, Semaphore[] forks, Semaphore maxDiners, int iterations) {
        this.id = id;
        this.forks = forks;
        this.maxDiners = maxDiners;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < iterations; i++) {
                think();
                maxDiners.acquire(); // Limit the number of philosophers at the table
                forks[id].acquire(); // Pick up left fork
                forks[(id + 1) % forks.length].acquire(); // Pick up right fork
                eat();
                forks[id].release(); // Put down left fork
                forks[(id + 1) % forks.length].release(); // Put down right fork
                maxDiners.release(); // Allow another philosopher to enter
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((int) (Math.random() * 1000));
    }
}

public class DiningPhilosophers {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        int iterations = 5; // Number of times each philosopher will think and eat
        Semaphore[] forks = new Semaphore[numPhilosophers];
        Semaphore maxDiners = new Semaphore(numPhilosophers - 1); // Limit the number of philosophers at the table

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks, maxDiners, iterations);
            philosophers[i].start();
        }

        // Wait for all philosophers to finish
        for (int i = 0; i < numPhilosophers; i++) {
            try {
                philosophers[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All philosophers have finished their meals.");
    }
}