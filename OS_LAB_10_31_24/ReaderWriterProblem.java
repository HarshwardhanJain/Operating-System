package OS_LAB_10_31_24;

import java.util.concurrent.Semaphore;

class ReaderWriter {
    private int readerCount = 0;
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore writeLock = new Semaphore(1);

    public void read(int readerId) throws InterruptedException {
        mutex.acquire();
        readerCount++;
        if (readerCount == 1) {
            writeLock.acquire();
        }
        mutex.release();

        // Reading section
        System.out.println("Reader " + readerId + " is reading.");
        Thread.sleep(1000); // Simulate reading time
        System.out.println("Reader " + readerId + " has finished reading.");

        mutex.acquire();
        readerCount--;
        if (readerCount == 0) {
            writeLock.release();
        }
        mutex.release();
    }

    public void write(int writerId) throws InterruptedException {
        writeLock.acquire();

        // Writing section
        System.out.println("Writer " + writerId + " is writing.");
        Thread.sleep(1000); // Simulate writing time
        System.out.println("Writer " + writerId + " has finished writing.");

        writeLock.release();
    }
}

class Reader extends Thread {
    private final ReaderWriter readerWriter;
    private final int readerId;

    public Reader(ReaderWriter readerWriter, int readerId) {
        this.readerWriter = readerWriter;
        this.readerId = readerId;
    }

    @Override
    public void run() {
        try {
            readerWriter.read(readerId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Writer extends Thread {
    private final ReaderWriter readerWriter;
    private final int writerId;

    public Writer(ReaderWriter readerWriter, int writerId) {
        this.readerWriter = readerWriter;
        this.writerId = writerId;
    }

    @Override
    public void run() {
        try {
            readerWriter.write(writerId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ReaderWriterProblem {
    public static void main(String[] args) {
        ReaderWriter readerWriter = new ReaderWriter();

        Reader reader1 = new Reader(readerWriter, 1);
        Reader reader2 = new Reader(readerWriter, 2);
        Writer writer1 = new Writer(readerWriter, 1);
        Writer writer2 = new Writer(readerWriter, 2);

        reader1.start();
        writer1.start();
        reader2.start();
        writer2.start();
    }
}