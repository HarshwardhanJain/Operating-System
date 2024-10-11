package OS_LAB_11_21_24;

import java.util.LinkedList;
import java.util.Queue;

class FIFOPageReplacement {
    private final int capacity;
    private final Queue<Integer> pageQueue;
    private int pageFaults;

    public FIFOPageReplacement(int capacity) {
        this.capacity = capacity;
        this.pageQueue = new LinkedList<>();
        this.pageFaults = 0;
    }

    public void accessPage(int pageNumber) {
        if (!pageQueue.contains(pageNumber)) {
            if (pageQueue.size() == capacity) {
                pageQueue.poll(); // Remove the oldest page
            }
            pageQueue.add(pageNumber);
            pageFaults++;
            System.out.println("Page " + pageNumber + " added. Page fault occurred.");
        } else {
            System.out.println("Page " + pageNumber + " accessed. No page fault.");
        }
        display();
    }

    public void display() {
        System.out.println("Current pages in memory: " + pageQueue);
        System.out.println();
    }

    public int getPageFaults() {
        return pageFaults;
    }
}

public class FIFOPageReplacementPolicy {
    public static void main(String[] args) {
        FIFOPageReplacement fifo = new FIFOPageReplacement(3);

        int[] pages = {1, 2, 3, 4, 1, 5, 1};

        System.out.println();
        for (int page : pages) {
            fifo.accessPage(page);
        }

        System.out.println("Total page faults: " + fifo.getPageFaults());
        System.out.println();
    }
}