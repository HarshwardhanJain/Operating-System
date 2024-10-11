package OS_LAB_11_21_24;

import java.util.*;

class OptimalPageReplacement {
    private final int capacity;
    private final List<Integer> pageList;
    private int pageFaults;

    public OptimalPageReplacement(int capacity) {
        this.capacity = capacity;
        this.pageList = new ArrayList<>();
        this.pageFaults = 0;
    }

    public void accessPage(int pageNumber, int[] futureReferences, int currentIndex) {
        if (!pageList.contains(pageNumber)) {
            if (pageList.size() == capacity) {
                int pageToReplace = findOptimalPageToReplace(futureReferences, currentIndex);
                pageList.remove((Integer) pageToReplace);
            }
            pageList.add(pageNumber);
            pageFaults++;
            System.out.println("Page " + pageNumber + " added. Page fault occurred.");
        } else {
            System.out.println("Page " + pageNumber + " accessed. No page fault.");
        }
        display();
    }

    private int findOptimalPageToReplace(int[] futureReferences, int currentIndex) {
        int farthest = currentIndex;
        int pageToReplace = -1;

        for (int page : pageList) {
            int nextUse = Integer.MAX_VALUE;
            for (int i = currentIndex + 1; i < futureReferences.length; i++) {
                if (futureReferences[i] == page) {
                    nextUse = i;
                    break;
                }
            }
            if (nextUse > farthest) {
                farthest = nextUse;
                pageToReplace = page;
            }
        }

        return pageToReplace == -1 ? pageList.get(0) : pageToReplace;
    }

    public void display() {
        System.out.println("Current pages in memory: " + pageList);
        System.out.println();
    }

    public int getPageFaults() {
        return pageFaults;
    }
}

public class OptimalPageReplacementPolicy {
    public static void main(String[] args) {
        OptimalPageReplacement optimal = new OptimalPageReplacement(3);

        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1};

        System.out.println();
        for (int i = 0; i < pages.length; i++) {
            optimal.accessPage(pages[i], pages, i);
        }

        System.out.println("Total page faults: " + optimal.getPageFaults());
        System.out.println();
    }
}